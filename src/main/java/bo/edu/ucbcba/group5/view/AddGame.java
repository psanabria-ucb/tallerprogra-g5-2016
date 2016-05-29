package bo.edu.ucbcba.group5.view;

import bo.edu.ucbcba.group5.controller.CompanyController;
import bo.edu.ucbcba.group5.controller.GameController;
import bo.edu.ucbcba.group5.exceptions.ValidationException;
import bo.edu.ucbcba.group5.model.Company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Abel on 5/29/2016.
 */
public class AddGame extends  JDialog{
    private JPanel rootPanel;
    private JTextField nameField;
    private JComboBox generoBox;
    private JComboBox desaBox;
    private JTextField lanField;
    private JTextField pesoField;
    private JTextArea descripArea;
    private JButton agregarButton;
    private GameController gameController=new GameController();
    private CompanyController companyController=new CompanyController();

    AddGame(GameWindow parent){
        super(parent,"Registrar juego",true);
        setContentPane(rootPanel);
        setSize(1600, 1400);
        pack();
        setResizable(true);
        populateComboBox();
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                launchRegistrar();
            }
        });

    }
    private void populateComboBox() {
        List<Company> companys =companyController.getAllCompanys();
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
        entro=true;
        Company c=(Company) desaBox.getSelectedItem();
        try {

            gameController.create(nameField.getText(),
                    (String) generoBox.getSelectedItem(),       // REGISTRA EL GENERO
                    descripArea.getText(),
                    lanField.getText(),
                    pesoField.getText(),c);

        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Format error", JOptionPane.ERROR_MESSAGE);
            entro=false;
        }
        if(entro) {
            JOptionPane.showMessageDialog(this, "Juego creado correctamente", "Realizado", JOptionPane.INFORMATION_MESSAGE);
            cancel();

        }

    }
}
