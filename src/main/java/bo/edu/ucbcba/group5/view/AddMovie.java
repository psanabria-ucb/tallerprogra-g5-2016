package bo.edu.ucbcba.group5.view;

import bo.edu.ucbcba.group5.controller.DirectorController;
import bo.edu.ucbcba.group5.controller.MovieController;
import bo.edu.ucbcba.group5.exceptions.ValidationException;
import bo.edu.ucbcba.group5.model.Directors;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


/**
 * Created by INTEL on 29/05/2016.
 */
public class AddMovie extends  JDialog{
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



    AddMovie(MovieWindow parent){
        super(parent,"Registrar nueva Película",true);
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

    private void populateComboBox(){
        List<Directors> directors = directorController.getAllDirectors();
        for (Directors c : directors) {
            directorBox.addItem(c);
        }
    }
    private void cancel() {
        setVisible(false);
        dispose();
    }

    private void registraPeli(){
        Boolean entro;
        entro=true;
        Directors d=(Directors) directorBox.getSelectedItem();
        try {

            movieController.create(tituloField.getText(),
                    (String) generoBox.getSelectedItem(),       // REGISTRA EL GENERO
                    descripcionArea.getText(),
                    lanzamientoField.getText(),
                    duracionField.getText(),
                    pesoField.getText(),d);

        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Format error", JOptionPane.ERROR_MESSAGE);
            entro=false;
        }
        if(entro) {
            JOptionPane.showMessageDialog(this, "Película agregada exitosamente ", "Realizado", JOptionPane.INFORMATION_MESSAGE);
            cancel();

        }
    }
}
