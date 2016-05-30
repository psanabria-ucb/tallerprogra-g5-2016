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
    private Double peso; // En Gb
    private int lanzamiento;
    private String compName;

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public Company getCompany() {
        return company;
    }
    public void setCompany(Company company) {
        this.company = company;
    }

    @ManyToOne
    private Company company;


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


}
