package bo.edu.ucbcba.group5.view;

import bo.edu.ucbcba.group5.controller.DigitalCenterController;
import bo.edu.ucbcba.group5.model.Elemento;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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

    BuscarElem(JFrame parent) {
        super(parent, "Buscar", true);
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
        final DefaultTableModel model = new DefaultTableModel();
        // model.addColumn("Id");
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

        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int columna = e.getColumn();
                    int fila = e.getFirstRow();
                    if (columna == 1) {

                        EntityManagerFactory emfactory = Persistence.
                                createEntityManagerFactory("DigitalCenter");
                        EntityManager entitymanager = emfactory.createEntityManager();
                        entitymanager.getTransaction().begin();
                        int cod = (Integer) model.getValueAt(resulTable.getSelectedRow(), 0);
                        Elemento elemento = entitymanager.find(Elemento.class, cod);


                        String nombr;

                        //before update
                        System.out.println(elemento);
                        elemento.setTitle(String.valueOf(model.getValueAt(fila, columna)));
                        entitymanager.getTransaction().commit();

                        //after update
                        System.out.println(elemento);
                        entitymanager.close();
                        emfactory.close();
                    }
                }
            }
        });


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
        rootPanel.setLayout(new GridLayoutManager(2, 2, new Insets(20, 20, 20, 20), -1, -1));
        rootPanel.setBackground(new Color(-13950744));
        nameField = new JTextField();
        nameField.setText("");
        rootPanel.add(nameField, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        buscarButton = new JButton();
        buscarButton.setText("Button");
        rootPanel.add(buscarButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        rootPanel.add(spacer1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        resulTable = new JTable();
        rootPanel.add(resulTable, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(500, 450), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}
