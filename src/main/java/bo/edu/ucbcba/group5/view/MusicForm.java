package bo.edu.ucbcba.group5.view;

import bo.edu.ucbcba.group5.controller.MusicController;
import bo.edu.ucbcba.group5.exceptions.ValidationException;
import bo.edu.ucbcba.group5.model.Musica;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.TreeCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by PC on 18/05/2016.
 */
public class MusicForm extends JDialog {
    private JTable resulTable;
    private JPanel rootPanel;
    private JButton buscarButton;
    private JTextField nameField;
    private JButton eliminarButton;
    private JButton actualizarButton;
    private JTextField nameField1;
    private JTextField genField;
    private JTextField descField;
    private JTextField lanField;
    private JTextField minuField;
    private JButton registrarButton;
    private JTextField pesoField;
    private JTextField song2Field;
    private JTextField song3Field;
    private JTextField song4Field;
    private JTextField song1Field;
    private JTextField song5Field;
    private JTextField song6Field;
    private JTextField song9Field;
    private JTextField song10Field;
    private JTextField song7Field;
    private JTextField song11Field;
    private JTextField song8Field;
    private JTextField song12Field;
    private JButton musicasButton;
    private JButton bandaButton;
    private JButton generoButton;
    private JTabbedPane tabbedPane1;
    private JTable songsTable;
    private JTextField textField1;
    private JButton editarButton;
    private JButton exportarATxtButton;
    private JButton imprimirButton;
    private JButton imprimirCancionesButton;
    private DefaultTableModel model;
    private DefaultTableModel model2;
    private MusicController musicController;

    MusicForm(JFrame parent) {
        super(parent, "Musicas", true);
        setContentPane(rootPanel);
        setSize(1600, 1400);
        pack();
        setResizable(true);
        song1Field.setVisible(false);
        song2Field.setVisible(false);
        song3Field.setVisible(false);
        song4Field.setVisible(false);
        song5Field.setVisible(false);
        song6Field.setVisible(false);
        song7Field.setVisible(false);
        song8Field.setVisible(false);
        song9Field.setVisible(false);
        song10Field.setVisible(false);
        song11Field.setVisible(false);
        song12Field.setVisible(false);
        textField1.setVisible(false);
        editarButton.setVisible(false);

        musicController = new MusicController();
        populateTable();
        populateTable2();
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                populateTable();
            }
        });
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                launchRegistrar();
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(resulTable.getSelectedColumn()==0)
                {
                    deleteElem();
                }
                else
                {
                    if(songsTable.getSelectedColumn()==0)
                    {
                        deleteElem2();
                    }
                    else
                        JOptionPane.showMessageDialog(null,"Debe seleccionar una fila!","No se puede Eliminar!",JOptionPane.WARNING_MESSAGE);

                }

            }
        });


        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(resulTable.getSelectedColumn()==0)
                {
                    launchUpdate2();
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Debe seleccionar una fila!","No se puede Actualizar!",JOptionPane.WARNING_MESSAGE);
                }

            }
        });
        /*
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(songsTable.getSelectedColumn()!= -1 || songsTable.getSelectedRow()!= -1)
                {
                    launchUpdate3();
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Debe seleccionar una fila!","No se puede Editar!",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        */

        populateTable();
        exportarATxtButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toTxt2();

            }
        });
        musicasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toTxt();

            }
        });
        bandaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderBandTable();
                orderBandTable2();
            }
        });
        generoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderGenTable();
                orderGenTable2();
            }
        });

        imprimirCancionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toPrint2();
            }
        });

        imprimirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toPrint();
            }
        });

        resulTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

                super.mouseClicked(mouseEvent);
                if(resulTable.getSelectedColumn()!= -1 || resulTable.getSelectedRow()!= -1)
                {
                    nameField1.setText((String) model.getValueAt(resulTable.getSelectedRow(), 0));
                    genField.setText((String) model.getValueAt(resulTable.getSelectedRow(), 1));
                    descField.setText((String) model.getValueAt(resulTable.getSelectedRow(), 2));
                    lanField.setText(String.valueOf((Integer) model.getValueAt(resulTable.getSelectedRow(), 3)));
                    minuField.setText(String.valueOf((Integer) model.getValueAt(resulTable.getSelectedRow(), 4)));
                    pesoField.setText((String) model.getValueAt(resulTable.getSelectedRow(), 5));
                    song1Field.setText((String) model2.getValueAt(resulTable.getSelectedRow(), 1));
                    song2Field.setText((String) model2.getValueAt(resulTable.getSelectedRow(), 2));
                    song3Field.setText((String) model2.getValueAt(resulTable.getSelectedRow(), 3));
                    song4Field.setText((String) model2.getValueAt(resulTable.getSelectedRow(), 4));
                    song5Field.setText((String) model2.getValueAt(resulTable.getSelectedRow(), 5));
                    song6Field.setText((String) model2.getValueAt(resulTable.getSelectedRow(), 6));
                    song7Field.setText((String) model2.getValueAt(resulTable.getSelectedRow(), 7));
                    song8Field.setText((String) model2.getValueAt(resulTable.getSelectedRow(), 8));
                    song9Field.setText((String) model2.getValueAt(resulTable.getSelectedRow(), 9));
                    song10Field.setText((String) model2.getValueAt(resulTable.getSelectedRow(), 10));
                    song11Field.setText((String) model2.getValueAt(resulTable.getSelectedRow(), 11));
                    song12Field.setText((String) model2.getValueAt(resulTable.getSelectedRow(), 12));
                }
            }
        });
        populateTable();
        populateTable2();
        model2.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {

                if(songsTable.getSelectedColumn()!= -1 || songsTable.getSelectedRow()!= -1)
                {
                    if(e.getType() == TableModelEvent.UPDATE)
                    {
                        int columna = e.getColumn();
                        int fila = e.getFirstRow();
                        if(columna == 1)
                        {
                            if (songsTable.getValueAt(fila,columna).toString().length() <21)
                            {
                                musicController.updatesong1((String) songsTable.getValueAt(fila,0),(String)songsTable.getValueAt(fila,columna));
                            }
                            else
                            {
                                String s = "";
                                JOptionPane.showMessageDialog(null,"Demasiados caracteres introducidos!","alerta",JOptionPane.ERROR_MESSAGE);
                                songsTable.setValueAt(s,fila,columna);
                            }
                        }
                        if(columna == 2)
                        {
                            if (songsTable.getValueAt(fila,columna).toString().length() <21)
                            {
                                musicController.updatesong2((String) songsTable.getValueAt(fila,0),(String)songsTable.getValueAt(fila,columna));
                            }
                            else
                            {
                                String s = "";
                                JOptionPane.showMessageDialog(null,"Demasiados caracteres introducidos!","alerta",JOptionPane.ERROR_MESSAGE);
                                songsTable.setValueAt(s,fila,columna);
                            }
                        }
                        if(columna == 3)
                        {
                            if (songsTable.getValueAt(fila,columna).toString().length() <21)
                            {
                                musicController.updatesong3((String) songsTable.getValueAt(fila,0),(String)songsTable.getValueAt(fila,columna));
                            }
                            else
                            {
                                String s = "";
                                JOptionPane.showMessageDialog(null,"Demasiados caracteres introducidos!","alerta",JOptionPane.ERROR_MESSAGE);
                                songsTable.setValueAt(s,fila,columna);
                            }
                        }
                        if(columna == 4)
                        {
                            if (songsTable.getValueAt(fila,columna).toString().length() <21)
                            {
                                musicController.updatesong4((String) songsTable.getValueAt(fila,0),(String)songsTable.getValueAt(fila,columna));
                            }
                            else
                            {
                                String s = "";
                                JOptionPane.showMessageDialog(null,"Demasiados caracteres introducidos!","alerta",JOptionPane.ERROR_MESSAGE);
                                songsTable.setValueAt(s,fila,columna);
                            }
                        }
                        if(columna == 5)
                        {
                            if (songsTable.getValueAt(fila,columna).toString().length() <21)
                            {
                                musicController.updatesong5((String) songsTable.getValueAt(fila,0),(String)songsTable.getValueAt(fila,columna));
                            }
                            else
                            {
                                String s = "";
                                JOptionPane.showMessageDialog(null,"Demasiados caracteres introducidos!","alerta",JOptionPane.ERROR_MESSAGE);
                                songsTable.setValueAt(s,fila,columna);
                            }
                        }
                        if(columna == 6)
                        {
                            if (songsTable.getValueAt(fila,columna).toString().length() <21)
                            {
                                musicController.updatesong6((String) songsTable.getValueAt(fila,0),(String)songsTable.getValueAt(fila,columna));
                            }
                            else
                            {
                                String s = "";
                                JOptionPane.showMessageDialog(null,"Demasiados caracteres introducidos!","alerta",JOptionPane.ERROR_MESSAGE);
                                songsTable.setValueAt(s,fila,columna);
                            }
                        }
                        if(columna == 7)
                        {
                            if (songsTable.getValueAt(fila,columna).toString().length() <21)
                            {
                                musicController.updatesong7((String) songsTable.getValueAt(fila,0),(String)songsTable.getValueAt(fila,columna));
                            }
                            else
                            {
                                String s = "";
                                JOptionPane.showMessageDialog(null,"Demasiados caracteres introducidos!","alerta",JOptionPane.ERROR_MESSAGE);
                                songsTable.setValueAt(s,fila,columna);
                            }
                        }
                        if(columna == 8)
                        {
                            if (songsTable.getValueAt(fila,columna).toString().length() <21)
                            {
                                musicController.updatesong8((String) songsTable.getValueAt(fila,0),(String)songsTable.getValueAt(fila,columna));
                            }
                            else
                            {
                                String s = "";
                                JOptionPane.showMessageDialog(null,"Demasiados caracteres introducidos!","alerta",JOptionPane.ERROR_MESSAGE);
                                songsTable.setValueAt(s,fila,columna);
                            }
                        }
                        if(columna == 9)
                        {
                            if (songsTable.getValueAt(fila,columna).toString().length() <21)
                            {
                                musicController.updatesong9((String) songsTable.getValueAt(fila,0),(String)songsTable.getValueAt(fila,columna));
                            }
                            else
                            {
                                String s = "";
                                JOptionPane.showMessageDialog(null,"Demasiados caracteres introducidos!","alerta",JOptionPane.ERROR_MESSAGE);
                                songsTable.setValueAt(s,fila,columna);
                            }
                        }
                        if(columna == 10)
                        {
                            if (songsTable.getValueAt(fila,columna).toString().length() <21)
                            {
                                musicController.updatesong10((String) songsTable.getValueAt(fila,0),(String)songsTable.getValueAt(fila,columna));
                            }
                            else
                            {
                                String s = "";
                                JOptionPane.showMessageDialog(null,"Demasiados caracteres introducidos!","alerta",JOptionPane.ERROR_MESSAGE);
                                songsTable.setValueAt(s,fila,columna);
                            }
                        }
                        if(columna == 11)
                        {
                            if (songsTable.getValueAt(fila,columna).toString().length() <21)
                            {
                                musicController.updatesong11((String) songsTable.getValueAt(fila,0),(String)songsTable.getValueAt(fila,columna));
                            }
                            else
                            {
                                String s = "";
                                JOptionPane.showMessageDialog(null,"Demasiados caracteres introducidos!","alerta",JOptionPane.ERROR_MESSAGE);
                                songsTable.setValueAt(s,fila,columna);
                            }
                        }
                        if(columna == 12)
                        {
                            if (songsTable.getValueAt(fila,columna).toString().length() <21)
                            {
                                musicController.updatesong12((String) songsTable.getValueAt(fila,0),(String)songsTable.getValueAt(fila,columna));
                            }
                            else
                            {
                                String s = "";
                                JOptionPane.showMessageDialog(null,"Demasiados caracteres introducidos!","alerta",JOptionPane.ERROR_MESSAGE);
                                songsTable.setValueAt(s,fila,columna);
                            }
                        }
                    }
                }

            }
        });
        /*
        songsTable.addMouseListener(new MouseAdapter() {

            @Override

            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                //String dato = String.valueOf(model2.getValueAt(songsTable.getSelectedRow(),songsTable.getSelectedColumn()));
                //textField1.setText(dato);

                if(resulTable.getSelectedColumn()!= -1 || resulTable.getSelectedRow()!= -1)
                {
                    song1Field.setText((String) model2.getValueAt(resulTable.getSelectedRow(), 1));
                    song2Field.setText((String) model2.getValueAt(resulTable.getSelectedRow(), 2));
                    song3Field.setText((String) model2.getValueAt(resulTable.getSelectedRow(), 3));
                    song4Field.setText((String) model2.getValueAt(resulTable.getSelectedRow(), 4));
                    song5Field.setText((String) model2.getValueAt(resulTable.getSelectedRow(), 5));
                    song6Field.setText((String) model2.getValueAt(resulTable.getSelectedRow(), 6));
                    song7Field.setText((String) model2.getValueAt(resulTable.getSelectedRow(), 7));
                    song8Field.setText((String) model2.getValueAt(resulTable.getSelectedRow(), 8));
                    song9Field.setText((String) model2.getValueAt(resulTable.getSelectedRow(), 9));
                    song10Field.setText((String) model2.getValueAt(resulTable.getSelectedRow(), 10));
                    song11Field.setText((String) model2.getValueAt(resulTable.getSelectedRow(), 11));
                    song12Field.setText((String) model2.getValueAt(resulTable.getSelectedRow(), 12));

                }
            }
        });
        */


    }
    private void styleTableWords()
    {

        songsTable.setFont(new Font(("nombre"),Font.BOLD,20));
        //songsTable.getColumnModel().getColumn(1).setMaxWidth(11);
        /*
        TableColumnModel columnModel = songsTable.getColumnModel();
        for(int i = 0; i< columnModel.getColumnCount();i++)
        {
            columnModel.getColumn(i).setMinWidth(4);
        }*/
    }
    private void toPrint()
    {
        try
        {
            resulTable.print();
        }
        catch(PrinterException ex)
        {
            Logger.getLogger(MusicForm.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    private void toPrint2()
    {
        try
        {
            songsTable.print();
        }
        catch(PrinterException ex)
        {
            Logger.getLogger(MusicForm.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    private void toTxt()
    {
        try
        {
            JFileChooser fi= new JFileChooser();
            fi.showSaveDialog(this);
            File file = fi.getSelectedFile();
            if(file != null)
            {
                FileWriter fw = new FileWriter(file+".txt");

                //FileWriter fw = new FileWriter((file.getAbsoluteFile()));
                BufferedWriter bw = new BufferedWriter(fw);
                for(int i=0; i <= resulTable.getRowCount();i++ )
                {
                    for(int j=0; j< resulTable.getColumnCount();j++)
                    {
                        if(i==0)
                        {
                            if(j==0)
                            {
                                bw.write("Album"+"\t" + "\t" +"\t" );
                            }
                            if(j==1)
                            {
                                bw.write("Genero"+"\t" + "\t" +"\t" );
                            }
                            if(j==2)
                            {
                                bw.write("Banda"+"\t" + "\t" +"\t" );
                            }
                            if(j==3)
                            {
                                bw.write("Lanzamiento"+"\t" + "\t" );
                            }
                            if(j==4)
                            {
                                bw.write("Pistas"+"\t" + "\t"+ "\t" );
                            }
                            if(j==5)
                            {
                                bw.write("Duracion"+"\t" + "\t" +"\t" );
                            }
                        }
                        else
                        {
                            String p=model.getValueAt(i-1,j)+"";
                            if(p.length()<8)
                                bw.write(model.getValueAt(i-1,j)+"\t" + "\t" +"\t" );
                            if(p.length()>=8 && p.length()<16)
                                bw.write(model.getValueAt(i-1,j)+"\t"+"\t");
                            if(p.length()>=16)
                                bw.write(model.getValueAt(i-1,j)+"\t");
                        }
                    }
                    bw.newLine();
                }
                bw.close();
                fw.close();
                JOptionPane.showMessageDialog(null,"Exportado Correctamente");
            }
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null,"El archivo no ha sido guardado","Advertencia!",JOptionPane.WARNING_MESSAGE);
        }
    }
    private void toTxt2()
    {
        try
        {
            JFileChooser fi= new JFileChooser();
            fi.showSaveDialog(this);
            File file = fi.getSelectedFile();
            if(file != null)
            {
                FileWriter fw = new FileWriter(file+".txt");

                //FileWriter fw = new FileWriter((file.getAbsoluteFile()));
                BufferedWriter bw = new BufferedWriter(fw);
                for(int i=0; i <= songsTable.getRowCount();i++ )
                {
                    for(int j=0; j< songsTable.getColumnCount();j++)
                    {
                        if(i==0)
                        {
                            if(j==0)
                            {
                                bw.write("Album"+"\t" + "\t" +"\t" );
                            }
                            if(j==1)
                            {
                                bw.write("Pista 1"+"\t" + "\t" +"\t" );
                            }
                            if(j==2)
                            {
                                bw.write("Pista 2"+"\t" + "\t" +"\t" );
                            }
                            if(j==3)
                            {
                                bw.write("Pista 3"+"\t" + "\t" +"\t" );
                            }
                            if(j==4)
                            {
                                bw.write("Pista 4"+"\t" + "\t"+ "\t" );
                            }
                            if(j==5)
                            {
                                bw.write("Pista 5"+"\t" + "\t" +"\t" );
                            }
                            if(j==6)
                            {
                                bw.write("Pista 6"+"\t" + "\t" +"\t" );
                            }
                            if(j==7)
                            {
                                bw.write("Pista 7"+"\t" + "\t" +"\t" );
                            }
                            if(j==8)
                            {
                                bw.write("Pista 8"+"\t" + "\t" +"\t" );
                            }
                            if(j==9)
                            {
                                bw.write("Pista 9"+"\t" + "\t" +"\t" );
                            }
                            if(j==10)
                            {
                                bw.write("Pista 10"+"\t" + "\t");
                            }
                            if(j==11)
                            {
                                bw.write("Pista 11"+"\t" + "\t");
                            }
                            if(j==12)
                            {
                                bw.write("Pista 12"+"\t" + "\t");
                            }
                        }
                        else
                        {
                            String p=model2.getValueAt(i-1,j)+"";
                            if(p.length()<8)
                                bw.write(model2.getValueAt(i-1,j)+"\t" + "\t" +"\t" );
                            if(p.length()>=8 && p.length()<16)
                                bw.write(model2.getValueAt(i-1,j)+"\t"+"\t");
                            if(p.length()>=16)
                                bw.write(model2.getValueAt(i-1,j)+"\t");
                        }
                    }
                    bw.newLine();
                }
                bw.close();
                fw.close();
                JOptionPane.showMessageDialog(null,"Exportado Correctamente");
            }
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null,"El archivo no ha sido guardado","Advertencia!",JOptionPane.WARNING_MESSAGE);
        }

    }

    private void Clean() {
        nameField1.setText("");
        genField.setText("");
        descField.setText("");
        lanField.setText("");
        minuField.setText("");
        pesoField.setText("");
        song1Field.setText("");
        song2Field.setText("");
        song3Field.setText("");
        song4Field.setText("");
        song5Field.setText("");
        song6Field.setText("");
        song7Field.setText("");
        song8Field.setText("");
        song9Field.setText("");
        song10Field.setText("");
        song11Field.setText("");
        song12Field.setText("");

    }

    private void launchSongWindow() {
        SongWindow form = new SongWindow(this);
        form.setVisible(true);
    }


    private void launchRegistrar() {
        Boolean accept;
        accept = true;
        try {
            musicController.create(nameField1.getText(),
                    genField.getText(),       // REGISTRA EL GENERO
                    descField.getText(),
                    lanField.getText(),
                    minuField.getText(),
                    pesoField.getText(),
                    song1Field.getText(),
                    song2Field.getText(),
                    song3Field.getText(),
                    song4Field.getText(),
                    song5Field.getText(),
                    song6Field.getText(),
                    song7Field.getText(),
                    song8Field.getText(),
                    song9Field.getText(),
                    song10Field.getText(),
                    song11Field.getText(),
                    song12Field.getText());

        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Format error", JOptionPane.ERROR_MESSAGE);
            accept = false;
        }

        if (accept)
            JOptionPane.showMessageDialog(this, "Album creado satisfactoriamente", "Realizado", JOptionPane.INFORMATION_MESSAGE);
        //cancel();

        populateTable();
        populateTable2();
    }

    private void launchUpdate() {
        try {

            musicController.update(nameField1.getText(),
                    genField.getText(),       // REGISTRA EL GENERO
                    descField.getText(),
                    lanField.getText(),
                    minuField.getText(),
                    pesoField.getText(),
                    song1Field.getText(),
                    song2Field.getText(),
                    song3Field.getText(),
                    song4Field.getText(),
                    song5Field.getText(),
                    song6Field.getText(),
                    song7Field.getText(),
                    song8Field.getText(),
                    song9Field.getText(),
                    song10Field.getText(),
                    song11Field.getText(),
                    song12Field.getText());

        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "error de formato", JOptionPane.ERROR_MESSAGE);
        }

        JOptionPane.showMessageDialog(this, "Musica actualizada satisfactoriamente", "Realizado", JOptionPane.INFORMATION_MESSAGE);
        //cancel();
        /*populateTable();
        Clean();*/

    }

    private void launchUpdate2()
    {

        DefaultTableModel model = (DefaultTableModel) resulTable.getModel();
        String cod = (String) model.getValueAt(resulTable.getSelectedRow(), 0);
        musicController.delete(cod);
        Boolean entro = true;
        try {

            musicController.create(nameField1.getText(),
                    genField.getText(),       // REGISTRA EL GENERO
                    descField.getText(),
                    lanField.getText(),
                    minuField.getText(),
                    pesoField.getText(),
                    song1Field.getText(),
                    song2Field.getText(),
                    song3Field.getText(),
                    song4Field.getText(),
                    song5Field.getText(),
                    song6Field.getText(),
                    song7Field.getText(),
                    song8Field.getText(),
                    song9Field.getText(),
                    song10Field.getText(),
                    song11Field.getText(),
                    song12Field.getText());

        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "error de formato", JOptionPane.ERROR_MESSAGE);
            entro = false;
        }
        if (entro) {
            JOptionPane.showMessageDialog(this, "Album actualizado satisfactoriamente", "Realizado", JOptionPane.INFORMATION_MESSAGE);
        }


        populateTable();
        populateTable2();
    }

    private void orderBandTable() {
        List<Musica> elementos = musicController.SearchSongsGenre(nameField.getText());
        model = new DefaultTableModel();
        model.addColumn("nombre");
        model.addColumn("genero");
        model.addColumn("Description");
        model.addColumn("lanzamiento");
        model.addColumn("Duración");
        model.addColumn("peso");
        resulTable.setModel(model);

        for (Musica m : elementos) {
            Object[] row = new Object[20];
            // row[0] = m.getId();
            row[0] = m.getNombre();
            row[1] = m.getGenero();
            row[2] = m.getDescription();
            row[3] = m.getLanzamiento();
            row[4] = m.getDuracMinutos();
            row[5] = String.format("%s", m.getPeso());
            model.addRow(row);
        }
    }



    private void orderGenTable() {
            List<Musica> elementos = musicController.SearchSongsBand(nameField.getText());
            model = new DefaultTableModel();
            model.addColumn("nombre");
            model.addColumn("genero");
        model.addColumn("Description");
        model.addColumn("lanzamiento");
        model.addColumn("Duración");
        model.addColumn("peso");

        resulTable.setModel(model);

        for (Musica m : elementos) {
            Object[] row = new Object[20];
            // row[0] = m.getId();
            row[0] = m.getNombre();
            row[1] = m.getGenero();
            row[2] = m.getDescription();
            row[3] = m.getLanzamiento();
            row[4] = m.getDuracMinutos();
            row[5] = String.format("%s", m.getPeso());

            model.addRow(row);
        }
    }


    private void populateTable() {
        List<Musica> elementos = musicController.BuscarMovies(nameField.getText());
        model = new DefaultTableModel();
        // model.addColumn("Id");
        model.addColumn("nombre");
        model.addColumn("genero");
        model.addColumn("Description");
        model.addColumn("lanzamiento");
        model.addColumn("Duración");
        model.addColumn("peso");

        //model.addColumn("tip");
        /*
        model.addColumn("cancion 1");

        model.addColumn("cancion 2");
        model.addColumn("cancion 3");
        model.addColumn("cancion 4");
        model.addColumn("cancion 5");
        model.addColumn("cancion 6");
        model.addColumn("cancion 7");
        model.addColumn("cancion 8");
        model.addColumn("cancion 9");
        model.addColumn("cancion 10");
        model.addColumn("cancion 11");
        model.addColumn("cancion 12");
    */
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(model);
        resulTable.setRowSorter(elQueOrdena);
        resulTable.setModel(model);

        for (Musica m : elementos) {
            Object[] row = new Object[20];
            // row[0] = m.getId();
            row[0] = m.getNombre();
            row[1] = m.getGenero();
            row[2] = m.getDescription();
            row[3] = m.getLanzamiento();
            row[4] = m.getDuracMinutos();
            row[5] = String.format("%s", m.getPeso());
            /*
            row[6] = m.getSong1();

            row[7] = m.getSong2();
            row[8] = m.getSong3();
            row[9] = m.getSong4();
            row[10] = m.getSong5();
            row[11] = m.getSong6();
            row[12] = m.getSong7();
            row[13] = m.getSong8();
            row[14] = m.getSong9();
            row[15] = m.getSong10();
            row[16] = m.getSong11();
            row[17] = m.getSong12();
            */
            model.addRow(row);
        }


        Clean();

    }
    private void orderBandTable2() {
        List<Musica> elementos = musicController.SearchSongsGenre(nameField.getText());
        model2 = new DefaultTableModel();
        // model.addColumn("Id");
        model2.addColumn("nombre");
        model2.addColumn("cancion 1");
        model2.addColumn("cancion 2");
        model2.addColumn("cancion 3");
        model2.addColumn("cancion 4");
        model2.addColumn("cancion 5");
        model2.addColumn("cancion 6");
        model2.addColumn("cancion 7");
        model2.addColumn("cancion 8");
        model2.addColumn("cancion 9");
        model2.addColumn("cancion 10");
        model2.addColumn("cancion 11");
        model2.addColumn("cancion 12");

        songsTable.setModel(model2);

        for (Musica m : elementos) {
            Object[] row = new Object[20];
            // row[0] = m.getId();
            row[0] = m.getNombre();
            row[1] = m.getSong1();
            row[2] = m.getSong2();
            row[3] = m.getSong3();
            row[4] = m.getSong4();
            row[5] = m.getSong5();
            row[6] = m.getSong6();
            row[7] = m.getSong7();
            row[8] = m.getSong8();
            row[9] = m.getSong9();
            row[10] = m.getSong10();
            row[11] = m.getSong11();
            row[12] = m.getSong12();

            model2.addRow(row);
        }
    }
    private void orderGenTable2() {
        List<Musica> elementos = musicController.SearchSongsBand(nameField.getText());
        model2 = new DefaultTableModel();
        // model.addColumn("Id");
        model2.addColumn("nombre");
        model2.addColumn("cancion 1");
        model2.addColumn("cancion 2");
        model2.addColumn("cancion 3");
        model2.addColumn("cancion 4");
        model2.addColumn("cancion 5");
        model2.addColumn("cancion 6");
        model2.addColumn("cancion 7");
        model2.addColumn("cancion 8");
        model2.addColumn("cancion 9");
        model2.addColumn("cancion 10");
        model2.addColumn("cancion 11");
        model2.addColumn("cancion 12");

        songsTable.setModel(model2);

        for (Musica m : elementos) {
            Object[] row = new Object[20];
            // row[0] = m.getId();
            row[0] = m.getNombre();
            row[1] = m.getSong1();
            row[2] = m.getSong2();
            row[3] = m.getSong3();
            row[4] = m.getSong4();
            row[5] = m.getSong5();
            row[6] = m.getSong6();
            row[7] = m.getSong7();
            row[8] = m.getSong8();
            row[9] = m.getSong9();
            row[10] = m.getSong10();
            row[11] = m.getSong11();
            row[12] = m.getSong12();

            model2.addRow(row);
        }
    }
    private void launchUpdate3()
    {

        DefaultTableModel model = (DefaultTableModel) songsTable.getModel();
        String cod = (String) model2.getValueAt(songsTable.getSelectedRow(), 0);
        musicController.delete(cod);
        Boolean entro = true;
        try {

            musicController.create(nameField1.getText(),
                    genField.getText(),       // REGISTRA EL GENERO
                    descField.getText(),
                    lanField.getText(),
                    minuField.getText(),
                    pesoField.getText(),
                    song1Field.getText(),
                    song2Field.getText(),
                    song3Field.getText(),
                    song4Field.getText(),
                    song5Field.getText(),
                    song6Field.getText(),
                    song7Field.getText(),
                    song8Field.getText(),
                    song9Field.getText(),
                    song10Field.getText(),
                    song11Field.getText(),
                    song12Field.getText());

        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "error de formato", JOptionPane.ERROR_MESSAGE);
            entro = false;
        }
        if (entro) {
            JOptionPane.showMessageDialog(this, "Album actualizado satisfactoriamente", "Realizado", JOptionPane.INFORMATION_MESSAGE);
        }
        populateTable();
        populateTable2();
    }
    private void populateTable2() {
        List<Musica> elementos = musicController.BuscarMovies(nameField1.getText());
        model2 = new DefaultTableModel();
        // model.addColumn("Id");
        model2.addColumn("nombre");
        model2.addColumn("cancion 1");
        model2.addColumn("cancion 2");
        model2.addColumn("cancion 3");
        model2.addColumn("cancion 4");
        model2.addColumn("cancion 5");
        model2.addColumn("cancion 6");
        model2.addColumn("cancion 7");
        model2.addColumn("cancion 8");
        model2.addColumn("cancion 9");
        model2.addColumn("cancion 10");
        model2.addColumn("cancion 11");
        model2.addColumn("cancion 12");

        songsTable.setModel(model2);

        for (Musica m : elementos) {
            Object[] row = new Object[20];
            // row[0] = m.getId();
            row[0] = m.getNombre();
            row[1] = m.getSong1();
            row[2] = m.getSong2();
            row[3] = m.getSong3();
            row[4] = m.getSong4();
            row[5] = m.getSong5();
            row[6] = m.getSong6();
            row[7] = m.getSong7();
            row[8] = m.getSong8();
            row[9] = m.getSong9();
            row[10] = m.getSong10();
            row[11] = m.getSong11();
            row[12] = m.getSong12();

            model2.addRow(row);
        }
        Clean();
    }

    public void deleteElem() {

        DefaultTableModel model = (DefaultTableModel) resulTable.getModel();
        String cod = (String) model.getValueAt(resulTable.getSelectedRow(), 0);
        musicController.delete(cod);
        JOptionPane.showMessageDialog(this, "Album eliminado correctamente", "Realizado", JOptionPane.INFORMATION_MESSAGE);
        populateTable();
        populateTable2();
    }
    public void deleteElem2() {

        DefaultTableModel model = (DefaultTableModel) songsTable.getModel();
        String cod = (String) model.getValueAt(songsTable.getSelectedRow(), 0);
        musicController.delete(cod);
        JOptionPane.showMessageDialog(this, "Album eliminado correctamente", "Realizado", JOptionPane.INFORMATION_MESSAGE);
        populateTable();
        populateTable2();
    }

    private void cancel() {
        setVisible(false);
        dispose();
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
        rootPanel.setLayout(new GridLayoutManager(15, 10, new Insets(0, 0, 0, 0), -1, -1));
        resulTable = new JTable();
        rootPanel.add(resulTable, new GridConstraints(1, 1, 5, 8, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(1000, 350), null, 0, false));
        nameField = new JTextField();
        rootPanel.add(nameField, new GridConstraints(0, 1, 1, 8, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        buscarButton = new JButton();
        buscarButton.setText("Buscar");
        rootPanel.add(buscarButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        eliminarButton = new JButton();
        eliminarButton.setText("Eliminar");
        rootPanel.add(eliminarButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        actualizarButton = new JButton();
        actualizarButton.setText("Actualizar");
        rootPanel.add(actualizarButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameField1 = new JTextField();
        rootPanel.add(nameField1, new GridConstraints(6, 1, 1, 8, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        genField = new JTextField();
        rootPanel.add(genField, new GridConstraints(7, 1, 1, 8, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lanField = new JTextField();
        rootPanel.add(lanField, new GridConstraints(9, 1, 1, 8, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        minuField = new JTextField();
        rootPanel.add(minuField, new GridConstraints(10, 1, 1, 8, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        registrarButton = new JButton();
        registrarButton.setText("Registrar");
        rootPanel.add(registrarButton, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        rootPanel.add(spacer1, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Album");
        rootPanel.add(label1, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Genero");
        rootPanel.add(label2, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Banda");
        rootPanel.add(label3, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Lanzamiento");
        rootPanel.add(label4, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Pistas");
        rootPanel.add(label5, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pesoField = new JTextField();
        rootPanel.add(pesoField, new GridConstraints(11, 1, 1, 8, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Minutos");
        rootPanel.add(label6, new GridConstraints(11, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Canciones");
        rootPanel.add(label7, new GridConstraints(12, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        song2Field = new JTextField();
        rootPanel.add(song2Field, new GridConstraints(12, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        song4Field = new JTextField();
        rootPanel.add(song4Field, new GridConstraints(12, 8, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        descField = new JTextField();
        rootPanel.add(descField, new GridConstraints(8, 1, 1, 8, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        song1Field = new JTextField();
        rootPanel.add(song1Field, new GridConstraints(12, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        song5Field = new JTextField();
        rootPanel.add(song5Field, new GridConstraints(13, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        song6Field = new JTextField();
        rootPanel.add(song6Field, new GridConstraints(13, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        song9Field = new JTextField();
        rootPanel.add(song9Field, new GridConstraints(14, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        song10Field = new JTextField();
        rootPanel.add(song10Field, new GridConstraints(14, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        song7Field = new JTextField();
        song7Field.setText("");
        rootPanel.add(song7Field, new GridConstraints(13, 6, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        song11Field = new JTextField();
        rootPanel.add(song11Field, new GridConstraints(14, 6, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        song8Field = new JTextField();
        rootPanel.add(song8Field, new GridConstraints(13, 8, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        song12Field = new JTextField();
        rootPanel.add(song12Field, new GridConstraints(14, 8, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("1");
        rootPanel.add(label8, new GridConstraints(12, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("2");
        rootPanel.add(label9, new GridConstraints(12, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setText("3");
        rootPanel.add(label10, new GridConstraints(12, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label11 = new JLabel();
        label11.setText("5");
        rootPanel.add(label11, new GridConstraints(13, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label12 = new JLabel();
        label12.setText("6");
        rootPanel.add(label12, new GridConstraints(13, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label13 = new JLabel();
        label13.setText("7");
        rootPanel.add(label13, new GridConstraints(13, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label14 = new JLabel();
        label14.setText("4");
        rootPanel.add(label14, new GridConstraints(12, 7, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label15 = new JLabel();
        label15.setText("9");
        rootPanel.add(label15, new GridConstraints(14, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label16 = new JLabel();
        label16.setText("8");
        rootPanel.add(label16, new GridConstraints(13, 7, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label17 = new JLabel();
        label17.setText("10");
        rootPanel.add(label17, new GridConstraints(14, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label18 = new JLabel();
        label18.setText("11");
        rootPanel.add(label18, new GridConstraints(14, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label19 = new JLabel();
        label19.setText("12");
        rootPanel.add(label19, new GridConstraints(14, 7, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        song3Field = new JTextField();
        rootPanel.add(song3Field, new GridConstraints(12, 6, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        musicasButton = new JButton();
        musicasButton.setText("Musicas");
        rootPanel.add(musicasButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label20 = new JLabel();
        label20.setText("Orden por");
        rootPanel.add(label20, new GridConstraints(0, 9, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        bandaButton = new JButton();
        bandaButton.setText("Album");
        rootPanel.add(bandaButton, new GridConstraints(1, 9, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        generoButton = new JButton();
        generoButton.setText("Genero");
        rootPanel.add(generoButton, new GridConstraints(2, 9, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}
