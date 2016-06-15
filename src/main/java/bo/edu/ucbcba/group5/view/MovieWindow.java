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
    private JComboBox directorBox;
    private JComboBox filtroBox;
    private JComboBox genBox;
    private JComboBox dirBox;
    private JButton editarButton;
    private JButton verButton;
    private DefaultTableModel model;
    private MovieController movieController;
    private DirectorController directorController = new DirectorController();

    MovieWindow(JFrame parent) {
        super(parent, "Películas", true);
        setContentPane(rootPanel);
        setSize(1600, 1400);
        pack();
        setResizable(false);
        movieController = new MovieController();
        populateTable();
        editarButton.setVisible(false);
        verButton.setVisible(false);
        eliminarButton.setVisible(false);
        agregarPeliculaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                launchAddMovieWindow();
                editarButton.setVisible(false);
                verButton.setVisible(false);
                eliminarButton.setVisible(false);
                populateTable();
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                populateTable();
                editarButton.setVisible(false);
                verButton.setVisible(false);
                eliminarButton.setVisible(false);
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
                editarButton.setVisible(false);
                verButton.setVisible(false);
                eliminarButton.setVisible(false);
            }
        });


        resulTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                editarButton.setVisible(true);
                verButton.setVisible(true);
                eliminarButton.setVisible(true);
                //nameField1.setText((String) model.getValueAt(resulTable.getSelectedRow(), 0));
                //genField.setText((String) model.getValueAt(resulTable.getSelectedRow(), 1));
                //descField.setText((String) model.getValueAt(resulTable.getSelectedRow(), 2));
                //minuField.setText(String.valueOf((Integer) model.getValueAt(resulTable.getSelectedRow(), 3)));
                //lanField.setText(String.valueOf((Integer) model.getValueAt(resulTable.getSelectedRow(), 4)));
                //pesoField.setText((String) model.getValueAt(resulTable.getSelectedRow(), 5));
                //directorField.setText((String) model.getValueAt(resulTable.getSelectedRow(), 6));
            }
        });
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                modificar();
                editarButton.setVisible(false);
                verButton.setVisible(false);
                eliminarButton.setVisible(false);
                populateTable();
            }
        });
        verButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ver();
                editarButton.setVisible(false);
                verButton.setVisible(false);
                eliminarButton.setVisible(false);
            }
        });

        agregarDirectorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                launchAddDirectorWindow();
                populatefiltroBox();
                editarButton.setVisible(false);
                verButton.setVisible(false);
                eliminarButton.setVisible(false);
//                setVisible(true);
//                populatefiltroBox();
//                populateComboBox();
            }
        });

    }

    public MovieWindow() {

        setContentPane(rootPanel);
        setSize(1600, 1400);
        pack();
        setResizable(true);

        movieController = new MovieController();
        populateTable();
        populatefiltroBox();
        populateComboBox();
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
                setVisible(false);
                launchAddDirectorWindow();
//                setVisible(true);
//                populatefiltroBox();
//                populateComboBox();
            }
        });
        resulTable.addMouseListener(new MouseAdapter() {
        });
    }

    private void populatefiltroBox() {
        java.util.List<Directors> director = directorController.getAllDirectors();
        int itemCount = dirBox.getItemCount();

        for (int i = 0; i < itemCount; i++) {
            dirBox.removeItemAt(0);
        }
        dirBox.addItem("Todos");
        for (Directors c : director) {
            dirBox.addItem(c.getName());
        }
    }

    private void populateComboBox() {
        java.util.List<Directors> directors = directorController.getAllDirectors();
        for (Directors c : directors) {
            directorBox.addItem(c);
        }
    }

    private void launchAddDirectorWindow() {
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

    private void modificar() {
        final String nom, genero, descrip, durac, lanz, peso, direct, lugar;
        nom = ((String) model.getValueAt(resulTable.getSelectedRow(), 0));
        genero = ((String) model.getValueAt(resulTable.getSelectedRow(), 1));
        descrip = ((String) model.getValueAt(resulTable.getSelectedRow(), 2));
        durac = (String.valueOf((Integer) model.getValueAt(resulTable.getSelectedRow(), 3)));
        lanz = (String.valueOf((Integer) model.getValueAt(resulTable.getSelectedRow(), 4)));
        peso = ((String) model.getValueAt(resulTable.getSelectedRow(), 5));
        direct = ((String) model.getValueAt(resulTable.getSelectedRow(), 6));
        lugar = ((String) model.getValueAt(resulTable.getSelectedRow(), 7));
        UpdateMovie form = new UpdateMovie(this, nom, genero, descrip, durac, lanz, peso, direct, lugar);
        form.setVisible(true);

    }

    private void ver() {
        final String nom, genero, descrip, durac, lanz, peso, direct, lugar;
        nom = ((String) model.getValueAt(resulTable.getSelectedRow(), 0));
        genero = ((String) model.getValueAt(resulTable.getSelectedRow(), 1));
        descrip = ((String) model.getValueAt(resulTable.getSelectedRow(), 2));
        durac = (String.valueOf((Integer) model.getValueAt(resulTable.getSelectedRow(), 3)));
        lanz = (String.valueOf((Integer) model.getValueAt(resulTable.getSelectedRow(), 4)));
        peso = ((String) model.getValueAt(resulTable.getSelectedRow(), 5));
        direct = ((String) model.getValueAt(resulTable.getSelectedRow(), 6));
        lugar = ((String) model.getValueAt(resulTable.getSelectedRow(), 7));
        VerMovieWindow formm = new VerMovieWindow(this, nom, genero, descrip, durac, lanz, peso, direct, lugar);
        formm.setVisible(true);
    }

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

   /* private void launchUpdate2() {
        DefaultTableModel model = (DefaultTableModel) resulTable.getModel();
        String cod = (String) model.getValueAt(resulTable.getSelectedRow(), 0);
        movieController.delete(cod);
        Directors d = (Directors) directorBox.getSelectedItem();
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
    */

    private void populateTable() {
        String direc = (String) dirBox.getSelectedItem();
        String gen = (String) genBox.getSelectedItem();
        String ord = (String) filtroBox.getSelectedItem();
        if (direc == "Todos") direc = "";
        if (gen == "Todos") gen = "";
        java.util.List<Pelicula> elementos = movieController.BuscarMovies(nameField.getText(), direc, gen, ord);
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
        model.addColumn("Directorio Portada");
        resulTable.setModel(model);

        for (Pelicula m : elementos) {
            Object[] row = new Object[8];
            // row[0] = m.getId();
            row[0] = m.getNombre();
            row[1] = m.getGenero();
            row[2] = m.getDescription();
            row[3] = m.getDuracMinutos();
            row[4] = m.getLanzamiento();
            row[5] = String.format("%s", m.getPeso());
            //row[6] = "Gbytes";
            row[6] = m.getDirectorName();
            row[7] = m.getNomCover();
            model.addRow(row);
        }

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


    private void createUIComponents() {
        // TODO: place custom component creation code here
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
        rootPanel.setLayout(new GridLayoutManager(11, 10, new Insets(20, 20, 20, 20), -1, -1));
        nameField = new JTextField();
        nameField.setText("");
        rootPanel.add(nameField, new GridConstraints(0, 0, 1, 9, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(395, 24), null, 0, false));
        buscarButton = new JButton();
        buscarButton.setText("Buscar");
        rootPanel.add(buscarButton, new GridConstraints(0, 9, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        resulTable = new JTable();
        rootPanel.add(resulTable, new GridConstraints(2, 0, 9, 9, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(500, 300), null, 0, false));
        final Spacer spacer1 = new Spacer();
        rootPanel.add(spacer1, new GridConstraints(6, 9, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        genBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Todos");
        defaultComboBoxModel1.addElement("Acción");
        defaultComboBoxModel1.addElement("Romántica");
        defaultComboBoxModel1.addElement("Terror");
        defaultComboBoxModel1.addElement("Comedia");
        defaultComboBoxModel1.addElement("Drama");
        defaultComboBoxModel1.addElement("Documental");
        defaultComboBoxModel1.addElement("Ficción");
        defaultComboBoxModel1.addElement("Infantil");
        genBox.setModel(defaultComboBoxModel1);
        rootPanel.add(genBox, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, -1), null, new Dimension(150, -1), 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Filtrar por:");
        rootPanel.add(label1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dirBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("Todos");
        dirBox.setModel(defaultComboBoxModel2);
        rootPanel.add(dirBox, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(150, -1), null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Ordenar por:");
        rootPanel.add(label2, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        filtroBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel3 = new DefaultComboBoxModel();
        defaultComboBoxModel3.addElement("Titulo");
        defaultComboBoxModel3.addElement("Género");
        defaultComboBoxModel3.addElement("Director");
        defaultComboBoxModel3.addElement("Lanzamiento");
        filtroBox.setModel(defaultComboBoxModel3);
        rootPanel.add(filtroBox, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, new Dimension(150, -1), 0, false));
        eliminarButton = new JButton();
        eliminarButton.setText("Eliminar");
        rootPanel.add(eliminarButton, new GridConstraints(1, 9, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        agregarPeliculaButton = new JButton();
        agregarPeliculaButton.setText("Agregar Pelicula");
        rootPanel.add(agregarPeliculaButton, new GridConstraints(2, 9, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        agregarDirectorButton = new JButton();
        agregarDirectorButton.setText("Agregar Director");
        rootPanel.add(agregarDirectorButton, new GridConstraints(3, 9, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        editarButton = new JButton();
        editarButton.setText("Editar");
        rootPanel.add(editarButton, new GridConstraints(4, 9, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        verButton = new JButton();
        verButton.setText("Ver");
        rootPanel.add(verButton, new GridConstraints(5, 9, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}
