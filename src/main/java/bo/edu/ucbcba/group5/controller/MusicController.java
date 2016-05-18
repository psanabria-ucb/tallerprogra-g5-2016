package bo.edu.ucbcba.group5.controller;

import bo.edu.ucbcba.group5.dao.DigitalCenterEntityManager;
import bo.edu.ucbcba.group5.exceptions.ValidationException;
import bo.edu.ucbcba.group5.model.Musica;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by PC on 18/05/2016.
 */
public class MusicController {
    public void create(String nombre, String genero, String description, String lanzamiento,String duracMinutos, String Gbpeso)
    {

        Musica musica = new Musica();
        musica.setNombre(nombre);
        musica.setGenero(genero);
        musica.setDescription(description);
        if (lanzamiento.matches("[0-9]+"))
            musica.setLanzamiento(Integer.parseInt(lanzamiento));
        else
            throw new ValidationException("El año de lanzamiento no es un número");
        Double p;
        p = Double.parseDouble(Gbpeso);
        musica.setPeso(p);

        if (duracMinutos.matches("[0-9]+"))
            musica.setDuracMinutos(Integer.parseInt(duracMinutos));
        else
            throw new ValidationException("La duración no esta en minutos");

        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(musica);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void update(String nombre, String genero, String description, String lanzamiento,String duracMinutos, String Gbpeso)
    {
        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        Musica musica = entityManager.find(Musica.class,nombre);
        musica.setNombre(nombre);
        musica.setGenero(genero);
        musica.setDescription(description);
        if (lanzamiento.matches("[0-9]+"))
            musica.setLanzamiento(Integer.parseInt(lanzamiento));
        else
            throw new ValidationException("El año de lanzamiento no es un número");
        Double p;
        p = Double.parseDouble(Gbpeso);
        musica.setPeso(p);

        if (duracMinutos.matches("[0-9]+"))
            musica.setDuracMinutos(Integer.parseInt(duracMinutos));
        else
            throw new ValidationException("La duración no esta en minutos");

        entityManager.persist(musica);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Musica> BuscarMovies(String q) {
        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        TypedQuery<Musica> query = entityManager.createQuery("select e from Musica e WHERE lower(e.nombre) like :nombre", Musica.class);
        query.setParameter("nombre", "%" + q.toLowerCase() + "%");
        // TypedQuery<Juego> query = entityManager.createQuery("select p from Juego p", Juego.class);
        List<Musica> response = query.getResultList();
        entityManager.close();
        return response;
    }
    public void delete(String id)
    {
        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Musica.class,id));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
