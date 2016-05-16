package bo.edu.ucbcba.group5.controller;
import bo.edu.ucbcba.group5.dao.DigitalCenterEntityManager;
import bo.edu.ucbcba.group5.exceptions.ValidationException;
import bo.edu.ucbcba.group5.model.Elemento;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DigitalCenterController {
    public void create(String title,
                       String gender,
                       String description,
                       String releaseYear,
                       int rating,
                       String hoursLength,
                       String minutesLength) {

        Elemento elemento = new Elemento();
        elemento.setTitle(title);
        elemento.setGender(gender);
        elemento.setDescription(description);
        if (releaseYear.matches("[0-9]+"))
            elemento.setReleaseYear(Integer.parseInt(releaseYear));
        else
            throw new ValidationException("Release year isn't a number");
        elemento.setRating(rating);

        int hours, minutes;

        if (!hoursLength.matches("[0-9]+"))
            throw new ValidationException("Year isn't a number");
        hours = Integer.parseInt(hoursLength);

        if (!minutesLength.matches("[0-9]+"))
            throw new ValidationException("Minutes field isn't a number");
        minutes = Integer.parseInt(minutesLength);

        if (minutes >= 60)
            throw new ValidationException("Minutes can't be greater than 59");
        elemento.setLength(hours * 60 + minutes);

        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(elemento);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Elemento> searchMovies(String q) {
        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        TypedQuery<Elemento> query = entityManager.createQuery("select e from Elemento e WHERE lower(e.title) like :title", Elemento.class);
        query.setParameter("title", "%" + q.toLowerCase() + "%");
        List<Elemento> response = query.getResultList();
        entityManager.close();
        return response;
    }
    public void delete(int id)
    {
        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Elemento.class,id));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
