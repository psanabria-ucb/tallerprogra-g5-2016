package bo.edu.ucbcba.group5.controller;

import bo.edu.ucbcba.group5.dao.DigitalCenterEntityManager;
import bo.edu.ucbcba.group5.exceptions.ValidationException;
import bo.edu.ucbcba.group5.model.Directors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by INTEL on 29/05/2016.
 */
public class DirectorController {

    public void saveDirector(String Name, String Anio, String osc) {
        EntityManager em = DigitalCenterEntityManager.createEntityManager();
        em.getTransaction().begin();
        Directors director = new Directors();
        if (Name.isEmpty()){
            throw new ValidationException("debe ingresar un nombre");

        }
        if (Anio.isEmpty()){
            throw new ValidationException("debe ingresar un año");
        }
        if (osc.isEmpty()){
            throw new ValidationException("Ingrese número de premios");
        }
        //if (address.isEmpty()){
        //     throw new ValidationException("Address can't be blank");
        // }
        int length;
        length = Name.length();
        if (length > 45)
            throw new ValidationException("El nombre no puede ser tan largo");
        else {
            director.setName(Name);
        }
        director.setName(Name);
        if(osc.matches("[0-9]+")) {
            director.setAwards(Integer.parseInt(osc));
        }
        else {
            throw new ValidationException("la cantidad de premios debe ser un número ");
        }

        if (Anio.matches("[0-9]+")) {
            if(Anio.length()<=4)
            director.setAnio(Integer.parseInt(Anio));
            else throw new ValidationException("el año no puede ser tan grande");
        }else {
            throw new ValidationException("el año no es un numero");
        }
        em.persist(director);
        em.getTransaction().commit();
        em.close();
    }

    public List<Directors> getAllDirectors() {
        EntityManager em = DigitalCenterEntityManager.createEntityManager();
        TypedQuery<Directors> query = em.createQuery("select d from Directors d order by d.Name", Directors.class);
        List<Directors> list = query.getResultList();
        em.close();
        return list;
    }
}
