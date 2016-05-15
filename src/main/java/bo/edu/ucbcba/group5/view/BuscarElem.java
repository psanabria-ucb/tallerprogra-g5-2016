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
public class BuscarElem extends JDialog {
    private JPanel rootPanel;
    private JTextField nameField;
    private JButton buscarButton;
    private JTable resulTable;
    private DigitalCenterController digitalCenterController;

    BuscarElem(JFrame parent){
        super(parent,"Buscar",true);
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
    }

    private void populateTable() {
        List<Elemento> elementos = digitalCenterController.searchMovies(nameField.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Title");
        model.addColumn("Gender");
        model.addColumn("Description");
        model.addColumn("Rating");
        model.addColumn("Release Year");
        model.addColumn("Length");
        resulTable.setModel(model);

        for (Elemento m : elementos) {
            Object[] row = new Object[6];

            row[0] = m.getTitle();
            row[1] = m.getGender();
            row[2] = m.getDescription();
            row[3] = m.getRating();
            row[4] = m.getReleaseYear();
            row[5] = String.format("%s,%s", m.getLength() / 60, m.getLength() % 60);
            model.addRow(row);
        }




    }


}
