package bo.edu.ucbcba.group5.controller;

import bo.edu.ucbcba.group5.dao.DigitalCenterEntityManager;
import bo.edu.ucbcba.group5.exceptions.ValidationException;
import bo.edu.ucbcba.group5.model.Elemento;
import bo.edu.ucbcba.group5.model.Juego;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Abel on 5/16/2016.
 */
public class GameController {
    public void create(String nombre, String genero, String description, String lanzamiento, String Gbpeso)
    {

        Juego juego = new Juego();
        juego.setNombre(nombre);
        juego.setGenero(genero);
        juego.setDescription(description);
        if (lanzamiento.matches("[0-9]+"))
            juego.setLanzamiento(Integer.parseInt(lanzamiento));
        else
            throw new ValidationException("Release year isn't a number");
        int p;
        p = Integer.parseInt(Gbpeso);
        juego.setPeso(p);

       /* int hours, minutes;

        if (!hoursLength.matches("[0-9]+"))
            throw new ValidationException("Year isn't a number");
        hours = Integer.parseInt(hoursLength);

        if (!minutesLength.matches("[0-9]+"))
            throw new ValidationException("Minutes field isn't a number");
        minutes = Integer.parseInt(minutesLength);

        if (minutes >= 60)
            throw new ValidationException("Minutes can't be greater than 59");
        elemento.setLength(hours * 60 + minutes);
*/
        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(juego);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Juego> BuscarGames(String q) {
        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        TypedQuery<Juego> query = entityManager.createQuery("select e from Juego e WHERE lower(e.nombre) like :nombre", Juego.class);
        query.setParameter("nombre", "%" + q.toLowerCase() + "%");
       // TypedQuery<Juego> query = entityManager.createQuery("select p from Juego p", Juego.class);
        List<Juego> response = query.getResultList();
        entityManager.close();
        return response;
    }
   public void delete(String id)
    {
        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Juego.class,id));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
