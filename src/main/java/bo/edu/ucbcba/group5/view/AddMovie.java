package bo.edu.ucbcba.group5.view;

import bo.edu.ucbcba.group5.controller.DirectorController;
import bo.edu.ucbcba.group5.controller.MovieController;
import bo.edu.ucbcba.group5.exceptions.ValidationException;
import bo.edu.ucbcba.group5.model.Directors;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


/**
 * Created by INTEL on 29/05/2016.
 */
public class AddMovie extends JDialog {
    private JPanel rootPanel;
    private JTextField tituloField;
    private JTextField lanzamientoField;
    private JTextField duracionField;
    private JTextField pesoField;
    private JTextArea descripcionArea;
    private JComboBox generoBox;
    private JComboBox directorBox;
    private JButton registrarButton;
    private MovieController movieController = new MovieController();
    private DirectorController directorController = new DirectorController();


    AddMovie(MovieWindow parent) {
        super(parent, "Registrar nueva Película", true);
        setContentPane(rootPanel);
        pack();
        setResizable(true);
        populateComboBox();
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                registraPeli();
            }
        });
    }

    private void populateComboBox() {
        List<Directors> directors = directorController.getAllDirectors();
        for (Directors c : directors) {
            directorBox.addItem(c);
        }
    }

    private void cancel() {
        setVisible(false);
        dispose();
    }

    private void registraPeli() {
        Boolean entro;
        entro = true;
        Directors d = (Directors) directorBox.getSelectedItem();
        try {

            movieController.create(tituloField.getText(),
                    (String) generoBox.getSelectedItem(),       // REGISTRA EL GENERO
                    descripcionArea.getText(),
                    lanzamientoField.getText(),
                    duracionField.getText(),
                    pesoField.getText(), d);

        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Format error", JOptionPane.ERROR_MESSAGE);
            entro = false;
        }
        if (entro) {
            JOptionPane.showMessageDialog(this, "Película agregada exitosamente ", "Realizado", JOptionPane.INFORMATION_MESSAGE);
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
        rootPanel.setLayout(new GridLayoutManager(5, 4, new Insets(20, 20, 20, 20), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("Titulo");
        rootPanel.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Lanzamiento");
        rootPanel.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Duración");
        rootPanel.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Peso");
        rootPanel.add(label4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Descripción");
        rootPanel.add(label5, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tituloField = new JTextField();
        tituloField.setText("");
        rootPanel.add(tituloField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lanzamientoField = new JTextField();
        rootPanel.add(lanzamientoField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        duracionField = new JTextField();
        rootPanel.add(duracionField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        pesoField = new JTextField();
        rootPanel.add(pesoField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        descripcionArea = new JTextArea();
        descripcionArea.setLineWrap(false);
        rootPanel.add(descripcionArea, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Género");
        rootPanel.add(label6, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        generoBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Acción");
        defaultComboBoxModel1.addElement("Romántica");
        defaultComboBoxModel1.addElement("Terror");
        defaultComboBoxModel1.addElement("Comedia");
        defaultComboBoxModel1.addElement("Drama");
        defaultComboBoxModel1.addElement("Documental");
        defaultComboBoxModel1.addElement("Ficción");
        defaultComboBoxModel1.addElement("Infantil");
        generoBox.setModel(defaultComboBoxModel1);
        rootPanel.add(generoBox, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(120, -1), null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Director");
        rootPanel.add(label7, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        directorBox = new JComboBox();
        rootPanel.add(directorBox, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(120, -1), null, null, 0, false));
        registrarButton = new JButton();
        registrarButton.setText("Registrar");
        rootPanel.add(registrarButton, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}
