package bo.edu.ucbcba.group5.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Abel on 5/28/2016.
 */
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 100)
    private String Name;
    private int Awards;
    private int Anio;

    public int getAnio() {
        return Anio;
    }

    public void setAnio(int anio) {
        Anio = anio;
    }

    @OneToMany
    private List<Juego> juegos;
    public Company() {
        id =0;
        Name = "";
        Awards = 0;
        juegos = new LinkedList<Juego>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAwards() {
        return Awards;
    }

    public void setAwards(int awards) {
        Awards = awards;
    }

    public List<Juego> getJuegos() {
        return juegos;
    }

    public void setJuegos(List<Juego> juegos) {
        this.juegos = juegos;
    }
    @Override
    public String toString() {
        return String.format("%s", Name);
    }
}
