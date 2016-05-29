package bo.edu.ucbcba.group5.view;

import bo.edu.ucbcba.group5.controller.DirectorController;
import bo.edu.ucbcba.group5.controller.MovieController;
import bo.edu.ucbcba.group5.exceptions.ValidationException;
import bo.edu.ucbcba.group5.model.Directors;
import bo.edu.ucbcba.group5.model.Pelicula;
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

/**
 * Created by INTEL on 17/05/2016.
 */
public class MovieWindow extends JDialog {
    private JTextField nameField;
    private JButton buscarButton;
    private JButton eliminarButton;
    private JButton actualizarButton;
    private JTable resulTable;
//    private JButton registrarButton;
    private JTextField nameField1;
    private JTextField genField;
    private JTextField descField;
    private JTextField lanField;
    private JTextField pesoField;
    private JTextField minuField;
    private JPanel rootPanel;
    private JButton agregarPeliculaButton;
    private JTextField directorField;
    private JButton agregarDirectorButton;
    private JComboBox generoBox;
    private JComboBox directorBox;
    private JComboBox filtroBox;
    private JComboBox genBox;
    private DefaultTableModel model;
    private MovieController movieController;
    private DirectorController directorController = new DirectorController();

    MovieWindow(JFrame parent) {
        super(parent, "Películas", true);
        setContentPane(rootPanel);
        setSize(1600, 1400);
        pack();
        setResizable(true);
        populateComboBox();
        movieController = new MovieController();
        populateTable();
        agregarPeliculaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                launchAddMovieWindow();
                populateTable();
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                populateTable();
            }
        });
       /* registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //launchRegistrar();
            }
        });*/
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
                minuField.setText(String.valueOf((Integer) model.getValueAt(resulTable.getSelectedRow(), 3)));
                lanField.setText(String.valueOf((Integer) model.getValueAt(resulTable.getSelectedRow(), 4)));
                pesoField.setText((String) model.getValueAt(resulTable.getSelectedRow(), 5));
                directorField.setText((String) model.getValueAt(resulTable.getSelectedRow(), 6));
            }
        });
        agregarDirectorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                launchAddDirectorWindow();
            }
        });
    }


    private void populateComboBox(){
        java.util.List<Directors> directors = directorController.getAllDirectors();
        for (Directors c : directors) {
            directorBox.addItem(c);
        }
    }

    private void launchAddDirectorWindow(){
        NewDirector form = new NewDirector(this);
        form.setVisible(true);
    }

    private void launchAddMovieWindow() {
        AddMovie form = new AddMovie(this);
        form.setVisible(true);
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
        directorField.setText("");

    }

    /*private void launchRegistrar() {
        try {

            movieController.create(nameField1.getText(),
                    genField.getText(),       // REGISTRA EL GENERO
                    descField.getText(),
                    lanField.getText(),
                    minuField.getText(),
                    pesoField.getText());

        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Format error", JOptionPane.ERROR_MESSAGE);
        }

        JOptionPane.showMessageDialog(this, "Película creada correctamente", "Realizado", JOptionPane.INFORMATION_MESSAGE);
        //cancel();

        populateTable();
    }*/

    private void launchUpdate() {
        try {

            movieController.update(nameField1.getText(),
                    genField.getText(),       // REGISTRA EL GENERO
                    descField.getText(),
                    lanField.getText(),
                    minuField.getText(),
                    pesoField.getText());

        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "error de formato", JOptionPane.ERROR_MESSAGE);
        }

        JOptionPane.showMessageDialog(this, "Película actualizada correctamente", "Realizado", JOptionPane.INFORMATION_MESSAGE);
        populateTable();
        Clean();

    }

    private void launchUpdate2() {
        DefaultTableModel model = (DefaultTableModel) resulTable.getModel();
        String cod = (String) model.getValueAt(resulTable.getSelectedRow(), 0);
        movieController.delete(cod);
        Directors d=(Directors) directorBox.getSelectedItem();
        try {

            movieController.create(nameField1.getText(),
                    (String) generoBox.getSelectedItem(),       // REGISTRA EL GENERO
                    descField.getText(),
                    lanField.getText(),
                    minuField.getText(),
                    pesoField.getText(), d);

        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "error de formato", JOptionPane.ERROR_MESSAGE);
        }
        JOptionPane.showMessageDialog(this, "Película actualizada correctamente", "Realizado", JOptionPane.INFORMATION_MESSAGE);
        populateTable();
    }

    private void populateTable() {
        //String comp= (String) comBox2.getSelectedItem();
        String gen=(String)genBox.getSelectedItem();
        String ord=(String)filtroBox.getSelectedItem();
        //if(comp=="Todos")comp="";
        if(gen=="Todos")gen="";
        java.util.List<Pelicula> elementos = movieController.BuscarMovies(nameField.getText(),gen,ord);
        model = new DefaultTableModel();
        // model.addColumn("Id");
        model.addColumn("Titulo");
        model.addColumn("Género");
        model.addColumn("Descripcion");
        model.addColumn("Duración");
        model.addColumn("Lanzamiento");
        model.addColumn("Peso");
        //model.addColumn("tip");
        model.addColumn("Director");
        resulTable.setModel(model);

        for (Pelicula m : elementos) {
            Object[] row = new Object[7];
            // row[0] = m.getId();
            row[0] = m.getNombre();
            row[1] = m.getGenero();
            row[2] = m.getDescription();
            row[3] = m.getDuracMinutos();
            row[4] = m.getLanzamiento();
            row[5] = String.format("%s", m.getPeso());
            //row[6] = "Gbytes";
            row[6]= m.getDirectorName();
            model.addRow(row);
        }
        Clean();

    }

    public void deleteElem() {

        DefaultTableModel model = (DefaultTableModel) resulTable.getModel();
        String cod = (String) model.getValueAt(resulTable.getSelectedRow(), 0);
        movieController.delete(cod);
        JOptionPane.showMessageDialog(this, "Película eliminada correctamente", "Realizado", JOptionPane.INFORMATION_MESSAGE);
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
        rootPanel.setLayout(new GridLayoutManager(11, 4, new Insets(20, 20, 20, 20), -1, -1));
        nameField = new JTextField();
        rootPanel.add(nameField, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(395, 24), null, 0, false));
        buscarButton = new JButton();
        buscarButton.setText("Buscar");
        rootPanel.add(buscarButton, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        eliminarButton = new JButton();
        eliminarButton.setText("Eliminar");
        rootPanel.add(eliminarButton, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        actualizarButton = new JButton();
        actualizarButton.setText("Actualizar");
        rootPanel.add(actualizarButton, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        resulTable = new JTable();
        rootPanel.add(resulTable, new GridConstraints(1, 0, 4, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(500, 400), null, 0, false));
        final Spacer spacer1 = new Spacer();
        rootPanel.add(spacer1, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Nombre");
        rootPanel.add(label1, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameField1 = new JTextField();
        rootPanel.add(nameField1, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(425, 24), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Género");
        rootPanel.add(label2, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Descripción");
        rootPanel.add(label3, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Lanzamiento");
        rootPanel.add(label4, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Peso");
        rootPanel.add(label5, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        genField = new JTextField();
        rootPanel.add(genField, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(425, 24), null, 0, false));
        descField = new JTextField();
        rootPanel.add(descField, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(425, 24), null, 0, false));
        lanField = new JTextField();
        rootPanel.add(lanField, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(425, 24), null, 0, false));
        pesoField = new JTextField();
        rootPanel.add(pesoField, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(425, 24), null, 0, false));
       // registrarButton = new JButton();
       // registrarButton.setText("Registrar");
       // rootPanel.add(registrarButton, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Gb");
        rootPanel.add(label6, new GridConstraints(9, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Duración");
        rootPanel.add(label7, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        minuField = new JTextField();
        rootPanel.add(minuField, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Minutos");
        rootPanel.add(label8, new GridConstraints(10, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
