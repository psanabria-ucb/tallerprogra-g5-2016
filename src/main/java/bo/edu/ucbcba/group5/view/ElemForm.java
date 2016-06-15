package bo.edu.ucbcba.group5.view;

import bo.edu.ucbcba.group5.controller.DigitalCenterController;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ElemForm extends JFrame {
    private JPanel rootPanel;

    private JButton createButton;
    private JButton buscarButton;
    private JButton eliminarButton;
    private JButton juegosButton;
    private JButton peliculasButton;
    private JButton musicasButton;
    private DigitalCenterController digitalCenterController;

    public ElemForm() {

        super("DigitalCenter");
        // launchImage();lo puse mas abajo xD y da
        setContentPane(rootPanel);
        // setSize(600, 400);
        createButton.setVisible(false);
        eliminarButton.setVisible(false);
        buscarButton.setVisible(false);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchRegister();
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                launchDelete();
            }
        });
        digitalCenterController = new DigitalCenterController();
        // populateTable();
        /*searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateTable();
            }
        });
        */
        juegosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                launchGameWindow();
            }
        });
        peliculasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                launchMovieWindow();
            }
        });
        musicasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchMusicWindow();
            }
        });
        launchImage();
        setSize(1300, 700);
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchBusqueda();
            }
        });

    }

    private void launchGameWindow() {
        GameWindow form = new GameWindow(this);
        form.setVisible(true);
    }

    private void launchMovieWindow() {
        MovieWindow form = new MovieWindow(this);
        form.setVisible(true);
    }

    private void launchMusicWindow() {
        MusicForm form = new MusicForm(this);
        form.setMinimumSize(new Dimension(950,650));
        form.setMaximumSize(new Dimension(1200,750));
        form.setPreferredSize(new Dimension(850,550));
        form.setVisible(true);
    }


    private void launchDelete() {
        DeleteElem form = new DeleteElem(this);

        form.setVisible(true);

    }

    private void launchRegister() {
        RegisterElemForm form = new RegisterElemForm(this);

        form.setVisible(true);

    }

    private void launchBusqueda() {
        BuscarElem form = new BuscarElem(this);
        form.setVisible(true);

    }

    private void launchImage() {

        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("foto.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setSize(900, 700);
        JLabel label = new JLabel(new ImageIcon(myPicture));
        ((JPanel) getContentPane()).setOpaque(false);
        getLayeredPane().add(label, JLayeredPane.FRAME_CONTENT_LAYER);
        label.setBounds(0, 0, myPicture.getWidth(), myPicture.getHeight());
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
        rootPanel.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        rootPanel.setBackground(new Color(-3206617));
        rootPanel.setBorder(BorderFactory.createTitledBorder(""));
        createButton = new JButton();
        createButton.setText("Registrar");
        rootPanel.add(createButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buscarButton = new JButton();
        buscarButton.setText("buscar");
        rootPanel.add(buscarButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        eliminarButton = new JButton();
        eliminarButton.setText("eliminar");
        rootPanel.add(eliminarButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        juegosButton = new JButton();
        juegosButton.setText("Juegos");
        rootPanel.add(juegosButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(400, 40), new Dimension(400, 40), new Dimension(400, 40), 0, false));
        peliculasButton = new JButton();
        peliculasButton.setText("Peliculas");
        rootPanel.add(peliculasButton, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(400, 40), new Dimension(400, 40), new Dimension(400, 40), 0, false));
        musicasButton = new JButton();
        musicasButton.setText("Musicas");
        rootPanel.add(musicasButton, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(400, 40), new Dimension(400, 40), new Dimension(400, 40), 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}
