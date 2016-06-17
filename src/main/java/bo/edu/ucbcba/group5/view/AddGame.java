package bo.edu.ucbcba.group5.view;

import bo.edu.ucbcba.group5.controller.CompanyController;
import bo.edu.ucbcba.group5.controller.GameController;
import bo.edu.ucbcba.group5.exceptions.ValidationException;
import bo.edu.ucbcba.group5.model.Company;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

/**
 * Created by Abel on 5/29/2016.
 */
public class AddGame extends JDialog {
    private JPanel rootPanel;
    private JTextField nameField;
    private JComboBox generoBox;
    private JComboBox desaBox;
    private JTextField lanField;
    private JTextField pesoField;
    private JTextArea descripArea;
    private JButton agregarButton;
    private JTextField imageText;
    private JButton buscarButton;
    private JLabel portada;
    private GameController gameController = new GameController();
    private CompanyController companyController = new CompanyController();

    AddGame(GameWindow parent) {
        super(parent, "Registrar juego", true);
        setContentPane(rootPanel);
        setSize(1600, 1400);
        setMinimumSize(new Dimension(600, 400));
        setMaximumSize(new Dimension(200, 200));
        pack();
        setResizable(true);
        populateComboBox();
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cargarImagen();
            }
        });
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                launchRegistrar();
            }
        });

    }

    private void cargarImagen() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
        chooser.setFileFilter(filtroImagen);
        int search = chooser.showOpenDialog(null);
        if (search == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            imageText.setText(String.valueOf(file));
            Image image = getToolkit().createImage(imageText.getText());
            image = image.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            portada.setIcon(new ImageIcon(image));
        }
    }

    private void populateComboBox() {
        List<Company> companys = companyController.getAllCompanys();
        for (Company c : companys) {
            desaBox.addItem(c);
        }
    }

    private void cancel() {
        setVisible(false);
        dispose();
    }

    private void launchRegistrar() {

        Boolean entro;
        entro = true;
        Company c = (Company) desaBox.getSelectedItem();
        try {

            gameController.create(nameField.getText(),
                    (String) generoBox.getSelectedItem(),       // REGISTRA EL GENERO
                    descripArea.getText(),
                    lanField.getText(),
                    pesoField.getText(), imageText.getText(), c);

        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de formato", JOptionPane.ERROR_MESSAGE);
            entro = false;
        }
        if (entro) {
            JOptionPane.showMessageDialog(this, "Juego creado correctamente", "Realizado", JOptionPane.INFORMATION_MESSAGE);
            cancel();

        }

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
        rootPanel.setLayout(new GridLayoutManager(9, 3, new Insets(20, 20, 20, 20), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("Descripcion");
        rootPanel.add(label1, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Peso(Gb)");
        rootPanel.add(label2, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Genero");
        rootPanel.add(label3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Lanzamiento");
        rootPanel.add(label4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Desarrollador");
        rootPanel.add(label5, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Nombre ");
        rootPanel.add(label6, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameField = new JTextField();
        rootPanel.add(nameField, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        generoBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Accion");
        defaultComboBoxModel1.addElement("Moba");
        defaultComboBoxModel1.addElement("Shooter");
        defaultComboBoxModel1.addElement("Estrategia");
        defaultComboBoxModel1.addElement("Rpg");
        generoBox.setModel(defaultComboBoxModel1);
        rootPanel.add(generoBox, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        desaBox = new JComboBox();
        rootPanel.add(desaBox, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lanField = new JTextField();
        rootPanel.add(lanField, new GridConstraints(3, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        pesoField = new JTextField();
        rootPanel.add(pesoField, new GridConstraints(4, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        agregarButton = new JButton();
        agregarButton.setIcon(new ImageIcon(getClass().getResource("/add.png")));
        agregarButton.setText("Agregar");
        rootPanel.add(agregarButton, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(300, -1), new Dimension(300, -1), new Dimension(300, -1), 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Portada");
        rootPanel.add(label7, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        imageText = new JTextField();
        rootPanel.add(imageText, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        buscarButton = new JButton();
        buscarButton.setIcon(new ImageIcon(getClass().getResource("/find.png")));
        buscarButton.setText("Buscar");
        rootPanel.add(buscarButton, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        portada = new JLabel();
        portada.setText("");
        rootPanel.add(portada, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(70, 70), new Dimension(70, 70), new Dimension(70, 70), 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        rootPanel.add(scrollPane1, new GridConstraints(5, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        descripArea = new JTextArea();
        descripArea.setColumns(3);
        descripArea.setLineWrap(true);
        descripArea.setRows(0);
        scrollPane1.setViewportView(descripArea);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}
