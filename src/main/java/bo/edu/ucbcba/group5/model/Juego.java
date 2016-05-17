package bo.edu.ucbcba.group5.model;

/**
 * Created by Abel on 5/16/2016.
 */
import javax.persistence.*;
@Entity
public class Juego {

    @Id
    private String nombre;


    @Column(length = 100)
    private String genero;

    @Lob
    @Column(length = 500)
    private String description;
    private int peso; // En Gb
    private int lanzamiento;


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

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getLanzamiento() {
        return lanzamiento;
    }

    public void setLanzamiento(int lanzamiento) {
        this.lanzamiento = lanzamiento;
    }
}
