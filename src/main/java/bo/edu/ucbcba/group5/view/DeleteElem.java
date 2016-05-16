package bo.edu.ucbcba.group5.view;

import bo.edu.ucbcba.group5.controller.DigitalCenterController;
import bo.edu.ucbcba.group5.model.Elemento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Abel on 5/15/2016.
 */
public class DeleteElem extends JDialog{
    private JTextField nameField;
    private JTable resulTable;
    private JPanel rootPanel;
    private JButton buscarButton;
    private JButton deleteButton;
    private DigitalCenterController digitalCenterController;

    DeleteElem(JFrame parent){
        super(parent,"borrar",true);
        setContentPane(rootPanel);
        setSize(1600, 1400);
        pack();
        setResizable(true);
        digitalCenterController = new DigitalCenterController();
        populateTable();
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                populateTable();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                deleteElem();
            }
        });
    }
    public void deleteElem(){

            DefaultTableModel model = (DefaultTableModel) resulTable.getModel();
            int cod = (Integer) model.getValueAt(resulTable.getSelectedRow(),0);
            digitalCenterController.delete(cod);

    }
    private void populateTable() {
        List<Elemento> elementos = digitalCenterController.searchMovies(nameField.getText());
        final DefaultTableModel model = new DefaultTableModel();
        //model.addColumn("Id");
        model.addColumn("Title");
        model.addColumn("Gender");
        model.addColumn("Description");
        model.addColumn("Rating");
        model.addColumn("Release Year");
        model.addColumn("Length");
        resulTable.setModel(model);

        for (Elemento m : elementos) {
            Object[] row = new Object[7];
            row[0] = m.getId();
            row[1] = m.getTitle();
            row[2] = m.getGender();
            row[3] = m.getDescription();
            row[4] = m.getRating();
            row[5] = m.getReleaseYear();
            row[6] = String.format("%s,%s", m.getLength() / 60, m.getLength() % 60);
            model.addRow(row);
        }



    }


}
