package bo.edu.ucbcba.group5.controller;

import bo.edu.ucbcba.group5.dao.DigitalCenterEntityManager;
import bo.edu.ucbcba.group5.exceptions.ValidationException;
import bo.edu.ucbcba.group5.model.Album;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Christian on 18/05/2016.
 */
public class AlbumController {
    public void create(String title, String artist, String description, String releaseYear, String numberTracks, String duration)
    {
        Album album = new Album();
        album.setTitle(title);
        album.setArtist(artist);
        album.setDescription(description);
        if(releaseYear.matches("[0-9]+"))
            album.setYear(Integer.parseInt(releaseYear));
        else
            throw new ValidationException("El año no es un numero!!");
        if(numberTracks.matches("[0-9]+"))
            album.setYear(Integer.parseInt(numberTracks));
        else
            throw new ValidationException("la pista no es un numero!!");
        if(duration.matches("[0-9]+"))
            album.setYear(Integer.parseInt(duration));
        else
            throw new ValidationException("la duracion no es un numero!!");
        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(album);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public List<Album> searchAlbums(String q) {
        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        TypedQuery<Album> query = entityManager.createQuery("select e from Album e WHERE lower(e.title) like :title", Album.class);
        query.setParameter("title", "%" + q.toLowerCase() + "%");
        List<Album> response = query.getResultList();
        entityManager.close();
        return response;
    }
    public void delete(String id)
    {
        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Album.class,id));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
