package bo.edu.ucbcba.group5.view;

import bo.edu.ucbcba.group5.controller.GameController;
import bo.edu.ucbcba.group5.exceptions.ValidationException;
import bo.edu.ucbcba.group5.model.Juego;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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



    GameWindow(JFrame parent) {
        super(parent, "Juegos", true);
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

    private void launchRegistrar() {
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
                if (e.getType() == TableModelEvent.UPDATE) {
                    int columna = e.getColumn();
                    int fila = e.getFirstRow();
                    if (columna == 0) {

                        EntityManagerFactory emfactory = Persistence.
                                createEntityManagerFactory("DigitalCenter");
                        EntityManager entitymanager = emfactory.createEntityManager();
                        entitymanager.getTransaction().begin();
                        String cod1 = (String) model.getValueAt(resulTable.getSelectedRow(), 0);
                        Juego elemento = entitymanager.find(Juego.class, cod1);

                        String nombr;

                        //before update
                        System.out.println(elemento);
                        elemento.setNombre(String.valueOf(model.getValueAt(fila, columna - 1)));
                        entitymanager.getTransaction().commit();

                        //after update
                        System.out.println(elemento);
                        entitymanager.close();
                        emfactory.close();
                    }
                }
            }
        });

    }

    public void deleteElem() {

        DefaultTableModel model = (DefaultTableModel) resulTable.getModel();
        String cod = (String) model.getValueAt(resulTable.getSelectedRow(), 0);
        gameController.delete(cod);

    }

    private void cancel() {
        setVisible(false);
        dispose();
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayoutManager(9, 9, new Insets(0, 0, 0, 0), -1, -1));
        nameField = new JTextField();
        nameField.setText("");
        rootPanel.add(nameField, new GridConstraints(0, 0, 1, 8, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        buscarButton = new JButton();
        buscarButton.setText("Buscar");
        rootPanel.add(buscarButton, new GridConstraints(0, 8, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        eliminarButton = new JButton();
        eliminarButton.setText("Eliminar");
        rootPanel.add(eliminarButton, new GridConstraints(1, 8, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        rootPanel.add(spacer1, new GridConstraints(3, 8, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        resulTable = new JTable();
        rootPanel.add(resulTable, new GridConstraints(1, 0, 3, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(80, 50), null, 0, false));
        final Spacer spacer2 = new Spacer();
        rootPanel.add(spacer2, new GridConstraints(4, 7, 5, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Lanzamiento");
        rootPanel.add(label1, new GridConstraints(7, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Peso");
        rootPanel.add(label2, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        rootPanel.add(spacer3, new GridConstraints(4, 6, 4, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Nombre");
        rootPanel.add(label3, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameField1 = new JTextField();
        rootPanel.add(nameField1, new GridConstraints(4, 3, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        descField = new JTextField();
        descField.setText("");
        rootPanel.add(descField, new GridConstraints(6, 3, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Genero");
        rootPanel.add(label4, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lanField = new JTextField();
        lanField.setText("");
        rootPanel.add(lanField, new GridConstraints(7, 3, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        genField = new JTextField();
        genField.setText("");
        rootPanel.add(genField, new GridConstraints(5, 3, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Descripcion");
        rootPanel.add(label5, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pesoField = new JTextField();
        pesoField.setText("");
        rootPanel.add(pesoField, new GridConstraints(8, 3, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        registrarButton = new JButton();
        registrarButton.setText("Registrar");
        rootPanel.add(registrarButton, new GridConstraints(4, 8, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}
