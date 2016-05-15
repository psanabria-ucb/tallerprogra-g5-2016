package bo.edu.ucbcba.group5;

import bo.edu.ucbcba.group5.view.MoviesForm;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        MoviesForm form = new MoviesForm();
        form.setVisible(true);
        form.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
