package bo.edu.ucbcba.group5.view;


import bo.edu.ucbcba.group5.controller.DirectorController;
import bo.edu.ucbcba.group5.exceptions.ValidationException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by INTEL on 29/05/2016.
 */
public class NewDirector extends  JDialog{
    private JPanel rootPanel;
    private JTextField nomdirField;
    private JTextField anioField;
    private JTextField premiosField;
    private JButton registrarDirButton;
    private DirectorController directorController;

    NewDirector(MovieWindow parent){
        super(parent,"Registrar director",true);
        setContentPane(rootPanel);
        pack();
        setResizable(true);
        directorController=new DirectorController();
        registrarDirButton.addActionListener(new ActionListener() {
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

            directorController.saveDirector(nomdirField.getText(),anioField.getText(),premiosField.getText());

        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Format error", JOptionPane.ERROR_MESSAGE);
            entro=false;
        }
        if(entro) {
            JOptionPane.showMessageDialog(this, "Director agregado exitosamente", "Realizado", JOptionPane.INFORMATION_MESSAGE);
            cancel();
        }

    }
    private void cancel() {
        setVisible(false);
        dispose();


    }
}
