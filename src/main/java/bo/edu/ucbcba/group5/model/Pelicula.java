package bo.edu.ucbcba.group5.model;

import javax.persistence.*;

/**
 * Created by INTEL on 17/05/2016.
 */
@Entity
public class Pelicula {

    @Id
    private String nombre;


    @Column(length = 100)
    private String genero;

    @Lob
    @Column(length = 500)
    private String description;
    private Double peso; // En Gb
    private int lanzamiento;
    private int duracMinutos;

    @ManyToOne
    private Directors director;

    public Directors getDirector() {
        return director;
    }

    public String getDirectorName(){
        return director.getName();
    }

    public void setDirector(Directors director) {
        this.director = director;
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


