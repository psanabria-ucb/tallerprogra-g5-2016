package bo.edu.ucbcba.group5.view;

import bo.edu.ucbcba.group5.controller.CompanyController;
import bo.edu.ucbcba.group5.exceptions.ValidationException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Abel on 5/28/2016.
 */
public class NewCompany extends JDialog{
    private CompanyController controller;
    private JPanel rootPanel;
    private JTextField nameField;
    private JTextField anioField;
    private JTextField premiosField;
    private JButton guardarButton;

    NewCompany(GameWindow parent){
        super(parent,"Registrar juego",true);
        setContentPane(rootPanel);
        setSize(1600, 1400);
        pack();
        setResizable(true);
        controller=new CompanyController();
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                launchRegistrar();
            }
        });
    }
    private void launchRegistrar() {
        Boolean entro;
        entro=true;
        try {

            controller.saveCompany(nameField.getText(),anioField.getText(),premiosField.getText());

        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Format error", JOptionPane.ERROR_MESSAGE);
            entro=false;
        }
        if(entro) {
            JOptionPane.showMessageDialog(this, "compania agregada correctamente", "Realizado", JOptionPane.INFORMATION_MESSAGE);
            cancel();
        }

    }
    private void cancel() {
        setVisible(false);
        dispose();


    }
}
