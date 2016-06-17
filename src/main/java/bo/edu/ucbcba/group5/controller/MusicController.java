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
    public void create(String nombre, String genero, String description, String lanzamiento,String duracMinutos, String Gbpeso, String song1, String song2, String song3, String song4, String song5, String song6, String song7, String song8, String song9, String song10, String song11, String song12)
    {

        Musica musica = new Musica();
        if (nombre.isEmpty())
        {
            throw new ValidationException("el campo nombre no puede quedar vacio!");
        }
        if (genero.isEmpty())
        {
            throw new ValidationException("el campo genero no puede quedar vacio!");
        }
        if (lanzamiento.isEmpty())
        {
            throw new ValidationException("el campo lanzamiento no puede quedar vacio!");
        }
        if (duracMinutos.isEmpty())
        {
            throw new ValidationException("el campo duracion minutos no puede quedar vacio!");
        }
        if (Gbpeso.isEmpty())
        {
            throw new ValidationException("el campo minutos no puede quedar vacio!");
        }

        int length = nombre.length();
        if (length > 23)
            throw new ValidationException("Album muy largo!");
        else {
            if(length<2)throw new ValidationException("Album muy corto!");
            else musica.setNombre(nombre);
        }

        int lengthgenero = genero.length();
        if (lengthgenero > 23)
            throw new ValidationException("Genero muy largo!");
        else {
            if(lengthgenero<2)throw new ValidationException("Genero muy corto!");
            else musica.setGenero(genero);
        }

        int lengthdesc = description.length();
        if (lengthdesc > 23)
            throw new ValidationException("Banda muy larga!");
        else {
            if(length<2)throw new ValidationException("Banda muy corta!");
            else musica.setDescription(description);
        }
        /*
        musica.setNombre(nombre);
        musica.setGenero(genero);
        musica.setDescription(description);
        */
        if (lanzamiento.matches("[0-9]+")) {
            if(lanzamiento.length()<=4) {
                if (Integer.parseInt(lanzamiento) <= 2016 && Integer.parseInt(lanzamiento) >= 1952) {
                    musica.setLanzamiento(Integer.parseInt(lanzamiento));

                } else
                    throw new ValidationException("El año no puede ser menor a 1952 o mayor a 2016");
            }else throw new ValidationException("El año es demasiado largo");

        }
        else
            throw new ValidationException("El año debe ser un numero");


        /*Double p;
        p = Double.parseDouble(Gbpeso);
        musica.setPeso(p);
        if(Gbpeso.matches("[0.0-9.9]+"))
            musica.setPeso(Double.parseDouble(Gbpeso));
        else {
            throw new ValidationException("Los minutos deben ser un numero!");
        }

        if(Gbpeso.matches("[0.0-9.9]+"))
        {
            if(Gbpeso.length()<=3)
            {
                if (Double.parseDouble(Gbpeso) <= 120 && Double.parseDouble(Gbpeso) >= 50)
                {
                    musica.setPeso(Double.parseDouble(Gbpeso));
                }
                else
                    throw new ValidationException("Los minutos deben estar entre 50 a 120");
            }
            else
                throw new ValidationException("Solo se admite entre 50 a 120 minutos");
        }
        else
            throw new ValidationException("El campo minutos debe ser un numero!");
            */
        if(Gbpeso.matches("[0.0-9.9]+"))
        {
            if (Double.parseDouble(Gbpeso) <= 120 && Double.parseDouble(Gbpeso) >= 50)
            {
                musica.setPeso(Double.parseDouble(Gbpeso));
            }
            else
                throw new ValidationException("Los minutos deben estar entre 50 a 120");
        }
        else
            throw new ValidationException("El campo minutos debe ser un numero!");


        if (song1.length() > 23)
            throw new ValidationException("Cancion muy larga!");
        else {
            if(song1.length()<0)throw new ValidationException("Nombre muy corto!");
            else musica.setSong1(song1);
        }

        if (song2.length() > 23)
            throw new ValidationException("Cancion muy larga!");
        else {
            if(song2.length()<0)throw new ValidationException("Nombre muy corto!");
            else musica.setSong2(song2);
        }
        if (song3.length() > 23)
            throw new ValidationException("Cancion muy larga!");
        else {
            if(song3.length()<0)throw new ValidationException("Nombre muy corto!");
            else musica.setSong3(song3);
        }
        if (song4.length() > 23)
            throw new ValidationException("Cancion muy larga!");
        else {
            if(song4.length()<0)throw new ValidationException("Nombre muy corto!");
            else musica.setSong4(song4);
        }
        if (song5.length() > 23)
            throw new ValidationException("Cancion muy larga!");
        else {
            if(song5.length()<0)throw new ValidationException("Nombre muy corto!");
            else musica.setSong5(song5);
        }
        if (song6.length() > 23)
            throw new ValidationException("Cancion muy larga!");
        else {
            if(song6.length()<0)throw new ValidationException("Nombre muy corto!");
            else musica.setSong6(song6);
        }
        if (song7.length() > 23)
            throw new ValidationException("Cancion muy larga!");
        else {
            if(song7.length()<0)throw new ValidationException("Nombre muy corto!");
            else musica.setSong7(song7);
        }
        if (song8.length() > 23)
            throw new ValidationException("Cancion muy larga!");
        else {
            if(song8.length()<0)throw new ValidationException("Nombre muy corto!");
            else musica.setSong8(song8);
        }
        if (song9.length() > 23)
            throw new ValidationException("Cancion muy larga!");
        else {
            if(song9.length()<0)throw new ValidationException("Nombre muy corto!");
            else musica.setSong9(song9);
        }
        if (song10.length() > 23)
            throw new ValidationException("Cancion muy larga!");
        else {
            if(song10.length()<0)throw new ValidationException("Nombre muy corto!");
            else musica.setSong10(song10);
        }
        if (song11.length() > 23)
            throw new ValidationException("Cancion muy larga!");
        else {
            if(song11.length()<0)throw new ValidationException("Nombre muy corto!");
            else musica.setSong11(song11);
        }
        if (song12.length() > 23)
            throw new ValidationException("Cancion muy larga!");
        else {
            if(song12.length()<0)throw new ValidationException("Nombre muy corto!");
            else musica.setSong12(song12);
        }



        /*
        musica.setSong1(song1);
        musica.setSong2(song2);
        musica.setSong3(song3);
        musica.setSong4(song4);
        musica.setSong5(song5);
        musica.setSong6(song6);
        musica.setSong7(song7);
        musica.setSong8(song8);
        musica.setSong9(song9);
        musica.setSong10(song10);
        musica.setSong11(song11);
        musica.setSong12(song12);
        */
        /*
        if (duracMinutos.matches("[0-9]+"))
            musica.setDuracMinutos(Integer.parseInt(duracMinutos));
        else
            throw new ValidationException("La duración no esta en minutos");
        */
        if (duracMinutos.matches("[0-9]+")) {
            if(duracMinutos.length()<=2) {
                if (Integer.parseInt(duracMinutos) <= 12 && Integer.parseInt(duracMinutos) >= 6) {
                    musica.setDuracMinutos(Integer.parseInt(duracMinutos));

                } else
                    throw new ValidationException("el numero de pistas debe ser mayor que 4 y menor que 13");
            }else throw new ValidationException("Solo se admite entre 5 a 12 pistas!");

        }
        else
            throw new ValidationException("La cantidad de pistas debe ser un numero!");

        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(musica);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void update(String nombre, String genero, String description, String lanzamiento,String duracMinutos, String Gbpeso, String song1, String song2, String song3, String song4, String song5, String song6, String song7, String song8, String song9, String song10, String song11, String song12)
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
        musica.setSong1(song1);
        musica.setSong2(song2);
        musica.setSong3(song3);
        musica.setSong4(song4);
        musica.setSong5(song5);
        musica.setSong6(song6);
        musica.setSong7(song7);
        musica.setSong8(song8);
        musica.setSong9(song9);
        musica.setSong10(song10);
        musica.setSong11(song11);
        musica.setSong12(song12);

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
    public List<Musica> SearchSongsGenre(String q) {
        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        TypedQuery<Musica> query = entityManager.createQuery("select e from Musica e WHERE lower(e.nombre) like :nombre order by e.nombre", Musica.class);
        query.setParameter("nombre", "%" + q.toLowerCase() + "%");
        // TypedQuery<Juego> query = entityManager.createQuery("select p from Juego p", Juego.class);
        List<Musica> response = query.getResultList();
        entityManager.close();
        return response;
    }
    public List<Musica> SearchSongsBand(String q) {
        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        TypedQuery<Musica> query = entityManager.createQuery("select e from Musica e WHERE lower(e.genero) like :genero order by e.genero", Musica.class);
        query.setParameter("genero", "%" + q.toLowerCase() + "%");
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
    public void updatesong1(String q, String song) {
        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        Musica m= entityManager.find(Musica.class,q);
        entityManager.getTransaction().begin();
        m.setSong1(song);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void updatesong2(String q, String song) {
        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        Musica m= entityManager.find(Musica.class,q);
        entityManager.getTransaction().begin();
        m.setSong2(song);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void updatesong3(String q, String song) {
        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        Musica m= entityManager.find(Musica.class,q);
        entityManager.getTransaction().begin();
        m.setSong3(song);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void updatesong4(String q, String song) {
        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        Musica m= entityManager.find(Musica.class,q);
        entityManager.getTransaction().begin();
        m.setSong4(song);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void updatesong5(String q, String song) {
        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        Musica m= entityManager.find(Musica.class,q);
        entityManager.getTransaction().begin();
        m.setSong5(song);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void updatesong6(String q, String song) {
        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        Musica m= entityManager.find(Musica.class,q);
        entityManager.getTransaction().begin();
        m.setSong6(song);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void updatesong7(String q, String song) {
        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        Musica m= entityManager.find(Musica.class,q);
        entityManager.getTransaction().begin();
        m.setSong7(song);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void updatesong8(String q, String song) {
        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        Musica m= entityManager.find(Musica.class,q);
        entityManager.getTransaction().begin();
        m.setSong8(song);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void updatesong9(String q, String song) {
        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        Musica m= entityManager.find(Musica.class,q);
        entityManager.getTransaction().begin();
        m.setSong9(song);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void updatesong10(String q, String song) {
        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        Musica m= entityManager.find(Musica.class,q);
        entityManager.getTransaction().begin();
        m.setSong10(song);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void updatesong11(String q, String song) {
        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        Musica m= entityManager.find(Musica.class,q);
        entityManager.getTransaction().begin();
        m.setSong11(song);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void updatesong12(String q, String song) {
        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        Musica m= entityManager.find(Musica.class,q);
        entityManager.getTransaction().begin();
        m.setSong12(song);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
