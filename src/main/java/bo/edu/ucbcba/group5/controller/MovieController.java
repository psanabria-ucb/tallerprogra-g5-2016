package bo.edu.ucbcba.group5.controller;

import bo.edu.ucbcba.group5.dao.DigitalCenterEntityManager;
import bo.edu.ucbcba.group5.exceptions.ValidationException;
import bo.edu.ucbcba.group5.model.Directors;
import bo.edu.ucbcba.group5.model.Pelicula;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by INTEL on 17/05/2016.
 */
public class MovieController {
    public void create(String nombre, String genero, String description, String lanzamiento, String duracMinutos, String Gbpeso, String cov, Directors d)
    {
        Pelicula pelicula = new Pelicula();
        if(d==null ){
            throw new ValidationException("Primero debe registrar al menos un director mediante -> Agregar Director");
        }
        if(nombre.isEmpty()){
            throw new ValidationException("Ingrese un Titulo");
        }
        if(genero.isEmpty()){
            throw new ValidationException("Ingrese un genero");
        }
        if(description.isEmpty()){
            throw new ValidationException("Ingrese una descripcion");
        }
        if(lanzamiento.isEmpty()){
            throw new ValidationException("Ingrese año de lanzamiento");
        }
        if(duracMinutos.isEmpty()){
            throw new ValidationException("Agregue la duracion en minutos");
        }
        if(Gbpeso.isEmpty()){
            throw new ValidationException("Ingrese el peso en GBytes");
        }
        if(cov.isEmpty()){
            throw new ValidationException("Debe seleccionar una imagen de portada");
        }
        int length;
        length = nombre.length();
        if(length>50) {
            throw new ValidationException("El Titulo es muy largo");
        }
        else{
            if(length<1) {
            throw new ValidationException("El Titulo es muy corto");
            }
            else{
                pelicula.setNombre(nombre);
            }
        }

        pelicula.setGenero(genero);
        pelicula.setNomCover(cov);
        pelicula.setDescription(description);
        pelicula.setDirector(d);
        pelicula.setDirname(d.getName());
        length = lanzamiento.length();

        if (lanzamiento.matches("[0-9]+")){
            if(lanzamiento.length()<=4) {
                if (Integer.parseInt(lanzamiento) <= 2016 && Integer.parseInt(lanzamiento) >= 1952) {
                    pelicula.setLanzamiento(Integer.parseInt(lanzamiento));
                } else
                    throw new ValidationException("El año no puede ser menor a 1952 o mayor a 2016");
            }else throw new ValidationException("El año no puede ser tan grande");
        }
        else
            throw new ValidationException("El año de lanzamiento no es un número");
        if(Gbpeso.matches("[0.0-9.9]+")){

            pelicula.setPeso(Double.parseDouble(Gbpeso));
        }
        Double p;
//        p = Double.parseDouble(Gbpeso);
//        pelicula.setPeso(p);
        length = duracMinutos.length();
        if (duracMinutos.matches("[0-9]+")) {
                if (length > 3)
                {
                    throw new ValidationException("La duración es muy larga");
                }
                else
                {
                    if (length < 1)
                    {
                        throw new ValidationException("La duración es muy corta");
                    }
                    else
                    {
    //                    if (Integer.parseInt(duracMinutos) <=999 && Integer.parseInt(duracMinutos) >= 1)
                            pelicula.setDuracMinutos(Integer.parseInt(duracMinutos));
  //                      else
  //                          throw new ValidationException("La duracion debe estar en rango 1 - 999 minutos");
                    }
                }
            }
            else
                throw new ValidationException("La duración no esta en minutos");

        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(pelicula);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void update(String nombre, String genero, String description, String lanzamiento,String duracMinutos, String Gbpeso)
    {
        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        Pelicula pelicula = entityManager.find(Pelicula.class,nombre);
        pelicula.setNombre(nombre);
        pelicula.setGenero(genero);
        pelicula.setDescription(description);
        if (lanzamiento.matches("[0-9]+")) {
            pelicula.setLanzamiento(Integer.parseInt(lanzamiento));
        }
        else {
            throw new ValidationException("El año de lanzamiento no es un número");
        }
        Double p;
        p = Double.parseDouble(Gbpeso);
        pelicula.setPeso(p);

        if (duracMinutos.matches("[0-9]+")) {
            pelicula.setDuracMinutos(Integer.parseInt(duracMinutos));
        }
        else {
            throw new ValidationException("La duración no esta en minutos");
        }




        entityManager.persist(pelicula);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Pelicula> BuscarMovies(String q,String direc,String Gender,String ord) {
        TypedQuery<Pelicula>query = null;
        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        //TypedQuery<Juego> query = entityManager.createQuery("select e from Juego e WHERE lower(e.nombre) like :nombre order by e.nombre", Juego.class);
        if(ord=="Titulo")
            query = entityManager.createQuery("select e from Pelicula e WHERE lower(e.nombre) like :nombre and lower(e.genero) like :genero and lower(e.dirname) like :dirname order by e.nombre", Pelicula.class);
        if(ord=="Género")
            query = entityManager.createQuery("select e from Pelicula e WHERE lower(e.nombre) like :nombre and lower(e.genero) like :genero and lower(e.dirname) like :dirname order by e.genero", Pelicula.class);
        if(ord=="Director")
            query = entityManager.createQuery("select e from Pelicula e WHERE lower(e.nombre) like :nombre and lower(e.genero) like :genero and lower(e.dirname) like :dirname order by e.director", Pelicula.class);
        if(ord=="Lanzamiento")
            query = entityManager.createQuery("select e from Pelicula e WHERE lower(e.nombre) like :nombre and lower(e.genero) like :genero and lower(e.dirname) like :dirname order by e.lanzamiento", Pelicula.class);

        query.setParameter("nombre", "%" + q.toLowerCase() + "%");
        query.setParameter("genero", "%" + Gender.toLowerCase() + "%");
        query.setParameter("dirname", "%" + direc.toLowerCase() + "%");
       // query.setParameter("lanzamiento", "%" + Gender.toLowerCase() + "%");

        List<Pelicula> response = query.getResultList();
        entityManager.close();
        return response;
    }
    public void delete(String id)
    {
        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Pelicula.class,id));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

