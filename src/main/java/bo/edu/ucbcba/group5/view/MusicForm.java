package bo.edu.ucbcba.group5.view;

import bo.edu.ucbcba.group5.controller.MusicController;
import bo.edu.ucbcba.group5.exceptions.ValidationException;
import bo.edu.ucbcba.group5.model.Musica;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Created by PC on 18/05/2016.
 */
public class MusicForm extends JDialog {
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

    MusicForm(JFrame parent) {
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
                    pesoField.getText(),
                    minuField.getText());

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
        List<Musica> elementos = musicController.BuscarMovies(nameField.getText());
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
            Object[] row = new Object[6];
            // row[0] = m.getId();
            row[0] = m.getNombre();
            row[1] = m.getGenero();
            row[2] = m.getDescription();
            row[3] = m.getLanzamiento();
            row[4] = m.getDuracMinutos();
            row[5] = String.format("%s", m.getPeso());

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
        rootPanel.setLayout(new GridLayoutManager(11, 2, new Insets(0, 0, 0, 0), -1, -1));
        resulTable = new JTable();
        rootPanel.add(resulTable, new GridConstraints(1, 1, 4, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(450, 250), null, 0, false));
        nameField = new JTextField();
        rootPanel.add(nameField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        buscarButton = new JButton();
        buscarButton.setText("Buscar");
        rootPanel.add(buscarButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        eliminarButton = new JButton();
        eliminarButton.setText("Eliminar");
        rootPanel.add(eliminarButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        actualizarButton = new JButton();
        actualizarButton.setText("Actualizar");
        rootPanel.add(actualizarButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameField1 = new JTextField();
        rootPanel.add(nameField1, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        genField = new JTextField();
        rootPanel.add(genField, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        descField = new JTextField();
        rootPanel.add(descField, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lanField = new JTextField();
        rootPanel.add(lanField, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        minuField = new JTextField();
        rootPanel.add(minuField, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        registrarButton = new JButton();
        registrarButton.setText("Registrar");
        rootPanel.add(registrarButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        rootPanel.add(spacer1, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Album");
        rootPanel.add(label1, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Genero");
        rootPanel.add(label2, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Banda");
        rootPanel.add(label3, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Lanzamiento");
        rootPanel.add(label4, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Pistas");
        rootPanel.add(label5, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pesoField = new JTextField();
        rootPanel.add(pesoField, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Minutos");
        rootPanel.add(label6, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}
