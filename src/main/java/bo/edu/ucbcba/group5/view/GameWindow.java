package bo.edu.ucbcba.group5.view;

import bo.edu.ucbcba.group5.controller.DigitalCenterController;
import bo.edu.ucbcba.group5.controller.GameController;
import bo.edu.ucbcba.group5.exceptions.ValidationException;
import bo.edu.ucbcba.group5.model.Elemento;
import bo.edu.ucbcba.group5.model.Juego;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Abel on 5/17/2016.
 */
public class GameWindow extends JDialog {
    private JPanel rootPanel;
    private JTable resulTable;
    private JButton buscarButton;
    private JButton eliminarButton;
    private JButton registrarButton;
    private JTextField nameField;
    private JTextField nameField1;
    private JTextField descField;
    private JTextField lanField;
    private JTextField genField;
    private JTextField pesoField;
    private GameController gameController;
    GameWindow(JFrame parent){
        super(parent,"Juegos",true);
        setContentPane(rootPanel);
        setSize(1600, 1400);
        pack();
        setResizable(true);
        gameController = new GameController();
        populateTable();
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                populateTable();
            }
        });
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                launchRegistrar();
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                deleteElem();
            }
        });
    }
    private void launchRegistrar(){
        try {

            gameController.create(nameField1.getText(),
                    genField.getText(),       // REGISTRA EL GENERO
                    descField.getText(),
                    lanField.getText(),
                    pesoField.getText());

        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Format error", JOptionPane.ERROR_MESSAGE);
        }

        JOptionPane.showMessageDialog(this, "Elemento created successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        //cancel();
    }
    private void populateTable() {
        List<Juego> elementos = gameController.BuscarGames(nameField.getText());
        final DefaultTableModel model = new DefaultTableModel();
        // model.addColumn("Id");
        model.addColumn("nombre");
        model.addColumn("genero");
        model.addColumn("Description");
        model.addColumn("lanzamiento");
        model.addColumn("peso");
        resulTable.setModel(model);

        for (Juego m : elementos) {
            Object[] row = new Object[5];
            // row[0] = m.getId();
            row[0] = m.getNombre();
            row[1] = m.getGenero();
            row[2] = m.getDescription();
            row[3] = m.getLanzamiento();
            row[4] = String.format("%s,%s", m.getPeso() / 60, m.getPeso() % 60);
            model.addRow(row);
        }
        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if(e.getType() == TableModelEvent.UPDATE){
                    int columna = e.getColumn();
                    int fila = e.getFirstRow();
                    if(columna == 0)
                    {

                        EntityManagerFactory emfactory = Persistence.
                                createEntityManagerFactory( "DigitalCenter" );
                        EntityManager entitymanager = emfactory.createEntityManager( );
                        entitymanager.getTransaction( ).begin( );
                        String cod1 = (String) model.getValueAt(resulTable.getSelectedRow(),0);
                        Juego elemento = entitymanager.find( Juego.class, cod1);

                        String nombr;

                        //before update
                        System.out.println( elemento);
                        elemento.setNombre(String.valueOf(model.getValueAt(fila,columna-1)));
                        entitymanager.getTransaction( ).commit( );

                        //after update
                        System.out.println( elemento );
                        entitymanager.close();
                        emfactory.close();
                    }
                }
            }
        });

    }
    public void deleteElem(){

        DefaultTableModel model = (DefaultTableModel) resulTable.getModel();
        String cod = (String) model.getValueAt(resulTable.getSelectedRow(),0);
        gameController.delete(cod);

    }
    private void cancel() {
        setVisible(false);
        dispose();
    }


}
