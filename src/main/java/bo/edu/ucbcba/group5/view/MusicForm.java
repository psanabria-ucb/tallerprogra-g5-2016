package bo.edu.ucbcba.group5.view;

import bo.edu.ucbcba.group5.controller.MusicController;
import bo.edu.ucbcba.group5.exceptions.ValidationException;
import bo.edu.ucbcba.group5.model.Musica;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by PC on 18/05/2016.
 */
public class MusicForm extends JDialog{
    private JTable resulTable;
    private JPanel rootPanel;
    private JButton buscarButton;
    private JTextField nameField;
    private JButton eliminarButton;
    private JButton actualizarButton;
    private JTextField nameField1;
    private JTextField genField;
    private JTextField descField;
    private JTextField lanField;
    private JTextField minuField;
    private JButton registrarButton;
    private JTextField pesoField;
    private DefaultTableModel model;
    private MusicController musicController;

    MusicForm (JFrame parent)
    {
        super(parent, "Musicas", true);
        setContentPane(rootPanel);
        setSize(1600, 1400);
        pack();
        setResizable(true);
        musicController = new MusicController();
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
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                launchUpdate2();
            }
        });

        resulTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                nameField1.setText((String) model.getValueAt(resulTable.getSelectedRow(), 0));
                genField.setText((String) model.getValueAt(resulTable.getSelectedRow(), 1));
                descField.setText((String) model.getValueAt(resulTable.getSelectedRow(), 2));
                lanField.setText(String.valueOf((Integer) model.getValueAt(resulTable.getSelectedRow(), 3)));
                minuField.setText(String.valueOf((Integer) model.getValueAt(resulTable.getSelectedRow(), 4)));
                pesoField.setText((String) model.getValueAt(resulTable.getSelectedRow(), 5));
            }
        });
    }

    private void Clean() {
        nameField1.setText("");
        genField.setText("");
        descField.setText("");
        lanField.setText("");
        // int p;
        // p = Integer.parseInt(Gbpeso);
        pesoField.setText("");
        minuField.setText("");

    }

    private void launchRegistrar() {
        try {

            musicController.create(nameField1.getText(),
                    genField.getText(),       // REGISTRA EL GENERO
                    descField.getText(),
                    lanField.getText(),
                    minuField.getText(),
                    pesoField.getText());

        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Format error", JOptionPane.ERROR_MESSAGE);
        }

        JOptionPane.showMessageDialog(this, "Musica creada satisfactoriamente", "Realizado", JOptionPane.INFORMATION_MESSAGE);
        //cancel();

        populateTable();
    }

    private void launchUpdate() {
        try {

            musicController.update(nameField1.getText(),
                    genField.getText(),       // REGISTRA EL GENERO
                    descField.getText(),
                    lanField.getText(),
                    minuField.getText(),
                    pesoField.getText());

        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "error de formato", JOptionPane.ERROR_MESSAGE);
        }

        JOptionPane.showMessageDialog(this, "Musica actualizada satisfactoriamente", "Realizado", JOptionPane.INFORMATION_MESSAGE);
        populateTable();
        Clean();

    }

    private void launchUpdate2() {
        DefaultTableModel model = (DefaultTableModel) resulTable.getModel();
        String cod = (String) model.getValueAt(resulTable.getSelectedRow(), 0);
        musicController.delete(cod);

        try {

            musicController.create(nameField1.getText(),
                    genField.getText(),       // REGISTRA EL GENERO
                    descField.getText(),
                    lanField.getText(),
                    minuField.getText(),
                    pesoField.getText());

        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "error de formato", JOptionPane.ERROR_MESSAGE);
        }
        JOptionPane.showMessageDialog(this, "Musica actualizada satisfactoriamente", "Realizado", JOptionPane.INFORMATION_MESSAGE);
        populateTable();
    }

    private void populateTable() {
        java.util.List<Musica> elementos = musicController.BuscarMovies(nameField.getText());
        model = new DefaultTableModel();
        // model.addColumn("Id");
        model.addColumn("nombre");
        model.addColumn("genero");
        model.addColumn("Description");
        model.addColumn("lanzamiento");
        model.addColumn("Duración");
        model.addColumn("peso");
        model.addColumn("tip");
        resulTable.setModel(model);

        for (Musica m : elementos) {
            Object[] row = new Object[7];
            // row[0] = m.getId();
            row[0] = m.getNombre();
            row[1] = m.getGenero();
            row[2] = m.getDescription();
            row[3] = m.getLanzamiento();
            row[4] = m.getDuracMinutos();
            row[5] = String.format("%s", m.getPeso());
            row[6] = "Gbytes";
            model.addRow(row);
        }
        Clean();

    }

    public void deleteElem() {

        DefaultTableModel model = (DefaultTableModel) resulTable.getModel();
        String cod = (String) model.getValueAt(resulTable.getSelectedRow(), 0);
        musicController.delete(cod);
        JOptionPane.showMessageDialog(this, "Musica eliminada correctamente", "Realizado", JOptionPane.INFORMATION_MESSAGE);
        populateTable();


    }

    private void cancel() {
        setVisible(false);
        dispose();
    }

}
