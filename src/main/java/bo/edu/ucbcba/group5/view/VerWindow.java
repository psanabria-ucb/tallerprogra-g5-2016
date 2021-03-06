package bo.edu.ucbcba.group5.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Abel on 5/31/2016.
 */
public class VerWindow extends JDialog {
    private JLabel nomLabel;
    private JLabel genlabel;
    private JLabel lanLabel;
    private JLabel comLabel;
    private JPanel rootPanel;
    private JLabel Image;

    VerWindow(GameWindow parent, final String nombre, String genero, String descrip, String lanz, String peso, String company, final String direc) {
        super(parent, " Detalles", true);
        setContentPane(rootPanel);
        setSize(1600, 1400);
        setMinimumSize(new Dimension(600, 600));
        setMaximumSize(new Dimension(200, 200));
        pack();
        setResizable(true);
        llenar(nombre, genero, descrip, lanz, company);
        Image image = getToolkit().createImage(direc);
        image = image.getScaledInstance(300, 300, image.SCALE_DEFAULT);
        Image.setIcon(new ImageIcon(image));
    }

    private void llenar(String nombre, String genero, String descrip, String lanz, String company) {
        nomLabel.setText(nombre);
        genlabel.setText(genero);
        lanLabel.setText(lanz);
        comLabel.setText(company);

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
        rootPanel.setLayout(new GridLayoutManager(3, 4, new Insets(20, 20, 20, 20), -1, -1));
        nomLabel = new JLabel();
        nomLabel.setText("Label");
        rootPanel.add(nomLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        rootPanel.add(spacer1, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        genlabel = new JLabel();
        genlabel.setText("Label");
        rootPanel.add(genlabel, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lanLabel = new JLabel();
        lanLabel.setText("Label");
        rootPanel.add(lanLabel, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comLabel = new JLabel();
        comLabel.setText("Label");
        rootPanel.add(comLabel, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Image = new JLabel();
        Image.setText("");
        rootPanel.add(Image, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(400, 300), new Dimension(400, 300), new Dimension(400, 400), 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Titulo:");
        rootPanel.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Lanzamiento:");
        rootPanel.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Genero:");
        rootPanel.add(label3, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Compañia:");
        rootPanel.add(label4, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}
