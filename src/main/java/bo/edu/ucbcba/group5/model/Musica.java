package bo.edu.ucbcba.group5.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * Created by PC on 18/05/2016.
 */
@Entity
public class Musica {
    @Id
    private String nombre;


    @Column(length = 100)
    private String genero;

    @Lob
    @Column(length = 500)
    private String description;
    private Double peso;
    private int lanzamiento;
    private int duracMinutos;
    private String song1;
    private String song2;
    private String song3;
    private String song4;
    private String song5;
    private String song6;
    private String song7;
    private String song8;
    private String song9;
    private String song10;
    private String song11;
    private String song12;


    public String getSong2() {
        return song2;
    }

    public void setSong2(String song2) {
        this.song2 = song2;
    }

    public String getSong3() {
        return song3;
    }

    public void setSong3(String song3) {
        this.song3 = song3;
    }

    public String getSong4() {
        return song4;
    }

    public void setSong4(String song4) {
        this.song4 = song4;
    }

    public String getSong5() {
        return song5;
    }

    public void setSong5(String song5) {
        this.song5 = song5;
    }

    public String getSong6() {
        return song6;
    }

    public void setSong6(String song6) {
        this.song6 = song6;
    }

    public String getSong7() {
        return song7;
    }

    public void setSong7(String song7) {
        this.song7 = song7;
    }

    public String getSong8() {
        return song8;
    }

    public void setSong8(String song8) {
        this.song8 = song8;
    }

    public String getSong9() {
        return song9;
    }

    public void setSong9(String song9) {
        this.song9 = song9;
    }

    public String getSong10() {
        return song10;
    }

    public void setSong10(String song10) {
        this.song10 = song10;
    }

    public String getSong11() {
        return song11;
    }

    public void setSong11(String song11) {
        this.song11 = song11;
    }

    public String getSong12() {
        return song12;
    }

    public void setSong12(String song12) {
        this.song12 = song12;
    }

    public String getSong1() {
        return song1;
    }

    public void setSong1(String song1) {
        this.song1 = song1;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public int getLanzamiento() {
        return lanzamiento;
    }

    public void setLanzamiento(int lanzamiento) {
        this.lanzamiento = lanzamiento;
    }

    public int getDuracMinutos() {return duracMinutos;   }

    public void setDuracMinutos(int duracMinutos) {  this.duracMinutos = duracMinutos; }

}
