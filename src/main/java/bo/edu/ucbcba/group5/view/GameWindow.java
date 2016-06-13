package bo.edu.ucbcba.group5.view;

import bo.edu.ucbcba.group5.controller.CompanyController;
import bo.edu.ucbcba.group5.controller.GameController;
import bo.edu.ucbcba.group5.dao.DigitalCenterEntityManager;
import bo.edu.ucbcba.group5.exceptions.ValidationException;
import bo.edu.ucbcba.group5.model.Company;
import bo.edu.ucbcba.group5.model.Juego;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.persistence.EntityManager;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Abel on 5/17/2016.
 */
public class GameWindow extends JDialog {
    private JPanel rootPanel;
    private JTable resulTable;
    private JButton buscarButton;
    private JButton eliminarButton;
    // private JButton registrarButton;
    private JTextField nameField;
    private JTextField nameField1;
    private JTextArea descArea;
    private JTextField lanField;
    private JTextField genField;
    private JTextField pesoField;
    private JButton actualizarButton;
    private JButton nuevoJuegoButton;
    private JComboBox genBox;
    private JComboBox comBox2;
    private JComboBox companyBox;
    private JButton agregarJuegoButton;
    private JComboBox catBox;
    private JComboBox geneBox1;
    private JTextField desField2;
    private JButton modificarButton;
    private JButton verButton;
    private JButton exportarAExcelButton;
    private GameController gameController;
    private DefaultTableModel model;
    private CompanyController companyControllerr = new CompanyController();
    private Juego aux;

    GameWindow(ElemForm parent) {
        super(parent, "Juegos", true);
        setContentPane(rootPanel);
        setSize(1200, 1000);
        pack();
        setResizable(false);
        gameController = new GameController();
        populateTable();
        modificarButton.setVisible(false);
        verButton.setVisible(false);
        eliminarButton.setVisible(false);
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                populateTable();
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (isSelect()) {
                    deleteElem();

                } else {
                    JOptionPane.showMessageDialog(GameWindow.this, "Ningun elemento seleccionado", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
                verButton.setVisible(false);
                modificarButton.setVisible(false);
                eliminarButton.setVisible(false);
            }
        });
        nuevoJuegoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                lauchNewgame();
                populatefiltroBox();
                modificarButton.setVisible(false);
                verButton.setVisible(false);


            }
        });
        agregarJuegoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                nuevoGame();
                modificarButton.setVisible(false);
                verButton.setVisible(false);
                populateTable();


            }
        });
        resulTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                modificarButton.setVisible(true);
                verButton.setVisible(true);
                eliminarButton.setVisible(true);
            }
        });
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (isSelect()) {
                    modificar();

                } else {
                    JOptionPane.showMessageDialog(GameWindow.this, "Ningun elemento seleccionado", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
                modificarButton.setVisible(false);
                verButton.setVisible(false);
                eliminarButton.setVisible(false);
                populateTable();
            }
        });
        verButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (isSelect()) {
                    ver();

                } else {
                    JOptionPane.showMessageDialog(GameWindow.this, "Ningun elemento seleccionado", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
                verButton.setVisible(false);
                modificarButton.setVisible(false);
                eliminarButton.setVisible(false);
            }
        });
        exportarAExcelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fc = new JFileChooser();
                int option = fc.showSaveDialog(GameWindow.this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    String filename = fc.getSelectedFile().getName();
                    String path = fc.getSelectedFile().getParentFile().getPath();

                    int len = filename.length();
                    String ext = "";
                    String file = "";

                    if (len > 4) {
                        ext = filename.substring(len - 4, len);
                    }

                    if (ext.equals(".xls")) {
                        file = path + "\\" + filename;
                    } else {
                        file = path + "\\" + filename + ".xls";
                    }

                    toExcel(new File(file));
                }

            }
        });

        populatefiltroBox();
    }

    public GameWindow() {
        // super("Juegos");

        setContentPane(rootPanel);
        setSize(1200, 1000);
        pack();
        setResizable(true);
        gameController = new GameController();
        populateTable();
        modificarButton.setVisible(false);
        verButton.setVisible(false);
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                populateTable();
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
        nuevoJuegoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // setVisible(false);
                lauchNewgame();
                populatefiltroBox();
                //populateComboBox();

            }
        });
        verButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ver();
                verButton.setVisible(false);
            }
        });
        agregarJuegoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                nuevoGame();
                populateTable();


            }
        });
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                modificar();

                modificarButton.setVisible(false);
                populateTable();
            }
        });
        resulTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                nameField1.setText((String) model.getValueAt(resulTable.getSelectedRow(), 0));
                genField.setText((String) model.getValueAt(resulTable.getSelectedRow(), 1));
                descArea.setText((String) model.getValueAt(resulTable.getSelectedRow(), 2));
                lanField.setText(String.valueOf((Integer) model.getValueAt(resulTable.getSelectedRow(), 3)));
                // int p;
                // p = Integer.parseInt(Gbpeso);
                pesoField.setText((String) model.getValueAt(resulTable.getSelectedRow(), 4));
                desField2.setText((String) model.getValueAt(resulTable.getSelectedRow(), 5));

            }
        });
        resulTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                modificarButton.setVisible(true);


            }
        });


        populatefiltroBox();

    }


    private void ComboBoxfiltro() {
        List<Company> companys = companyControllerr.getAllCompanys();
        for (Company c : companys) {
            companyBox.addItem(c);
        }
    }

    private Boolean isSelect() {
        if (resulTable.isColumnSelected(0) || resulTable.isColumnSelected(1) ||
                resulTable.isColumnSelected(2) || resulTable.isColumnSelected(3) || resulTable.isColumnSelected(4) ||
                resulTable.isColumnSelected(5) || resulTable.isColumnSelected(6) || resulTable.isColumnSelected(7)) {
            return true;

        } else {
            return false;
        }

    }

    private void populatefiltroBox() {
        List<Company> companys = companyControllerr.getAllCompanys();
        int itemCount = comBox2.getItemCount();

        for (int i = 0; i < itemCount; i++) {
            comBox2.removeItemAt(0);
        }
        comBox2.addItem("Todos");
        for (Company c : companys) {
            comBox2.addItem(c.getName());
        }
    }

    private void lauchNewgame() {
        NewCompany form = new NewCompany(this);
        form.setVisible(true);

    }

    private void ver() {
        final String nom, genero, descrip, lanz, peso, compania, direc;
        nom = ((String) model.getValueAt(resulTable.getSelectedRow(), 0));
        genero = ((String) model.getValueAt(resulTable.getSelectedRow(), 1));
        descrip = ((String) model.getValueAt(resulTable.getSelectedRow(), 2));
        lanz = (String.valueOf((Integer) model.getValueAt(resulTable.getSelectedRow(), 3)));
        // int p;
        // p = Integer.parseInt(Gbpeso);
        peso = ((String) model.getValueAt(resulTable.getSelectedRow(), 4));
        compania = ((String) model.getValueAt(resulTable.getSelectedRow(), 5));
        direc = ((String) model.getValueAt(resulTable.getSelectedRow(), 6));
        VerWindow f = new VerWindow(this, nom, genero, descrip, lanz, peso, compania, direc);
        f.setVisible(true);

    }

    private void toExcel(File file) {
        try {

            FileWriter excel = new FileWriter(file);

            for (int i = 0; i < resulTable.getColumnCount(); i++) {
                excel.write(resulTable.getColumnName(i) + "\t");
            }

            excel.write("\n");


            for (int i = 0; i < resulTable.getRowCount(); i++) {
                for (int j = 0; j < resulTable.getColumnCount(); j++) {
                    String text = resulTable.getValueAt(i, j).toString();


                    String aux = text.replaceAll("\\r\\n|\\r|\\n", " ");
                    excel.write(aux + "\t");
                }
                excel.write("\n");
            }

            excel.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void modificar() {
        final String nom, genero, descrip, lanz, peso, compania, direc;
        nom = ((String) model.getValueAt(resulTable.getSelectedRow(), 0));
        genero = ((String) model.getValueAt(resulTable.getSelectedRow(), 1));
        descrip = ((String) model.getValueAt(resulTable.getSelectedRow(), 2));
        lanz = (String.valueOf((Integer) model.getValueAt(resulTable.getSelectedRow(), 3)));
        // int p;
        // p = Integer.parseInt(Gbpeso);
        peso = ((String) model.getValueAt(resulTable.getSelectedRow(), 4));
        compania = ((String) model.getValueAt(resulTable.getSelectedRow(), 5));
        direc = ((String) model.getValueAt(resulTable.getSelectedRow(), 6));
        UpdateGame form = new UpdateGame(this, nom, genero, descrip, lanz, peso, compania, direc);
        form.setVisible(true);
    }

    private void nuevoGame() {
        AddGame form = new AddGame(this);
        form.setVisible(true);

    }

    private void Clean() {
        nameField1.setText("");
        genField.setText("");
        descArea.setText("");
        lanField.setText("");
        // int p;
        // p = Integer.parseInt(Gbpeso);
        pesoField.setText("");
    }

  /*  private void launchRegistrar() {
        Boolean entro;
        entro = true;
        Company c = (Company) companyBox.getSelectedItem();
        try {

            gameController.create(nameField1.getText(),
                    genField.getText(),       // REGISTRA EL GENERO
                    descArea.getText(),
                    lanField.getText(),
                    pesoField.getText(), c);

        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Format error", JOptionPane.ERROR_MESSAGE);
            entro = false;
        }
        if (entro) {
            JOptionPane.showMessageDialog(this, "Juego creado correctamente", "Realizado", JOptionPane.INFORMATION_MESSAGE);
            Clean();
        }
        populateTable();
    }

    private void error() {
        JOptionPane.showMessageDialog(this, "Juego creado correctamente", "Realizado", JOptionPane.INFORMATION_MESSAGE);
    }*/

    private void launchUpdate() {
        Boolean entro;
        entro = true;
        try {

            gameController.update(nameField1.getText(),
                    genField.getText(),       // REGISTRA EL GENERO
                    descArea.getText(),
                    lanField.getText(),
                    pesoField.getText());

        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "error de formato", JOptionPane.ERROR_MESSAGE);
            entro = false;
        }
        if (entro)
            JOptionPane.showMessageDialog(this, "Elemento actualizado correctamente", "Realizado", JOptionPane.INFORMATION_MESSAGE);
        populateTable();


    }

    private void launchUpdate2() {
        EntityManager entityManager2 = DigitalCenterEntityManager.createEntityManager();
        entityManager2.getTransaction().begin();
        Juego aux = entityManager2.find(Juego.class, nameField1.getText());
        entityManager2.getTransaction().commit();
        entityManager2.close();

        DefaultTableModel model = (DefaultTableModel) resulTable.getModel();
        String cod = (String) model.getValueAt(resulTable.getSelectedRow(), 0);
        gameController.delete(cod);
        Boolean entro = true;
        String co = aux.getNomCover();
        Company c = (Company) companyBox.getSelectedItem();
        try {

            gameController.create(nameField1.getText(),
                    (String) geneBox1.getSelectedItem(),       // REGISTRA EL GENERO
                    descArea.getText(),
                    lanField.getText(),
                    pesoField.getText(), " ", c);

        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "error de formato", JOptionPane.ERROR_MESSAGE);
            entro = false;


        }
        if (entro) {
            JOptionPane.showMessageDialog(this, "Elemento actualizado correctamente", "Realizado", JOptionPane.INFORMATION_MESSAGE);

        }

        populateTable();
    }

    private void populateTable() {
        String comp = (String) comBox2.getSelectedItem();
        String gen = (String) genBox.getSelectedItem();
        String ord = (String) catBox.getSelectedItem();
        if (comp == "Todos") comp = "";
        if (gen == "Todos") gen = "";
        List<Juego> elementos = gameController.BuscarGames(nameField.getText(), comp, gen, ord);
        model = new DefaultTableModel();

        // model.addColumn("Id");
        model.addColumn("nombre");
        model.addColumn("genero");
        model.addColumn("Description");
        model.addColumn("lanzamiento");
        model.addColumn("peso");
        model.addColumn("compania");
        model.addColumn("directorio de la portada");
        resulTable.setModel(model);

        for (Juego m : elementos) {
            Object[] row = new Object[7];
            // row[0] = m.getId();
            row[0] = m.getNombre();
            row[1] = m.getGenero();
            row[2] = m.getDescription();
            row[3] = m.getLanzamiento();
            row[4] = String.format("%s", m.getPeso());
            row[5] = m.getCompName();
            row[6] = m.getNomCover();
            model.addRow(row);
        }


    }

    public void deleteElem() {

        DefaultTableModel model = (DefaultTableModel) resulTable.getModel();
        String cod = (String) model.getValueAt(resulTable.getSelectedRow(), 0);
        gameController.delete(cod);
        JOptionPane.showMessageDialog(this, "Elemento eliminado correctamente", "Realizado", JOptionPane.INFORMATION_MESSAGE);
        populateTable();


    }

    private void cancel() {
        setVisible(false);
        dispose();
    }


    private void createUIComponents() {

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
        rootPanel.setLayout(new GridLayoutManager(24, 17, new Insets(20, 20, 20, 20), -1, -1));
        nameField = new JTextField();
        nameField.setText("");
        rootPanel.add(nameField, new GridConstraints(0, 0, 1, 16, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer1 = new Spacer();
        rootPanel.add(spacer1, new GridConstraints(20, 16, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        resulTable = new JTable();
        rootPanel.add(resulTable, new GridConstraints(3, 0, 19, 16, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(500, 450), null, 0, false));
        comBox2 = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Todos");
        comBox2.setModel(defaultComboBoxModel1);
        rootPanel.add(comBox2, new GridConstraints(1, 1, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        genBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("Todos");
        defaultComboBoxModel2.addElement("Accion");
        defaultComboBoxModel2.addElement("Rpg");
        defaultComboBoxModel2.addElement("Moba");
        defaultComboBoxModel2.addElement("Shooter");
        defaultComboBoxModel2.addElement("Estrategia");
        genBox.setModel(defaultComboBoxModel2);
        rootPanel.add(genBox, new GridConstraints(1, 2, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Ordenar por");
        rootPanel.add(label1, new GridConstraints(1, 6, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        rootPanel.add(spacer2, new GridConstraints(15, 16, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        rootPanel.add(spacer3, new GridConstraints(23, 16, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        rootPanel.add(spacer4, new GridConstraints(14, 16, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        rootPanel.add(spacer5, new GridConstraints(10, 16, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        buscarButton = new JButton();
        buscarButton.setText("Buscar");
        rootPanel.add(buscarButton, new GridConstraints(0, 16, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Filtrar por:");
        rootPanel.add(label2, new GridConstraints(1, 0, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        rootPanel.add(spacer6, new GridConstraints(22, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        nuevoJuegoButton = new JButton();
        nuevoJuegoButton.setText("Nuevo Desarrollador");
        rootPanel.add(nuevoJuegoButton, new GridConstraints(5, 16, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        eliminarButton = new JButton();
        eliminarButton.setText("Eliminar");
        rootPanel.add(eliminarButton, new GridConstraints(6, 16, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        agregarJuegoButton = new JButton();
        agregarJuegoButton.setText("Agregar juego");
        rootPanel.add(agregarJuegoButton, new GridConstraints(3, 16, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        catBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel3 = new DefaultComboBoxModel();
        defaultComboBoxModel3.addElement("Nombre");
        defaultComboBoxModel3.addElement("Genero");
        defaultComboBoxModel3.addElement("Compañia");
        defaultComboBoxModel3.addElement("Lanzamiento");
        catBox.setModel(defaultComboBoxModel3);
        rootPanel.add(catBox, new GridConstraints(1, 9, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        rootPanel.add(spacer7, new GridConstraints(1, 11, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer8 = new Spacer();
        rootPanel.add(spacer8, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer9 = new Spacer();
        rootPanel.add(spacer9, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer10 = new Spacer();
        rootPanel.add(spacer10, new GridConstraints(1, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        modificarButton = new JButton();
        modificarButton.setText("Modificar");
        rootPanel.add(modificarButton, new GridConstraints(7, 16, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        verButton = new JButton();
        verButton.setText("Ver");
        rootPanel.add(verButton, new GridConstraints(8, 16, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        exportarAExcelButton = new JButton();
        exportarAExcelButton.setText("Exportar a excel");
        rootPanel.add(exportarAExcelButton, new GridConstraints(9, 16, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}
