package bo.edu.ucbcba.group5.controller;

import bo.edu.ucbcba.group5.dao.DigitalCenterEntityManager;
import bo.edu.ucbcba.group5.exceptions.ValidationException;
//import bo.edu.ucbcba.group5.model.Elemento;
import bo.edu.ucbcba.group5.model.Company;
import bo.edu.ucbcba.group5.model.Juego;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Abel on 5/16/2016.
 */
public class GameController {
    public void create(String nombre, String genero, String description, String lanzamiento, String Gbpeso,String cov, Company c)
    {

        Juego juego = new Juego();
        if (c==null){
            throw new ValidationException("primero debe ir ala opcion registrar nueva desarrolladora para poder agregar un juego");
        }
        if (nombre.isEmpty()){
            throw new ValidationException("debe ingresar un nombre");

        }
        if (lanzamiento.isEmpty()){
            throw new ValidationException("debe ingresar un año");
        }

        if (description.isEmpty()){
            throw new ValidationException("debe ingresar una descripccion");
        }
        if (Gbpeso.isEmpty()){
            throw new ValidationException("debe ingresar un peso en Gb");
        }
        if(cov.isEmpty()){
            throw new ValidationException("debe seleccionar una imagen");
        }
        int length;
        length = nombre.length();
        if (length > 100)
            throw new ValidationException("el nombre es demasiado largo");
        else {
            if(length<2)throw new ValidationException("el nombre es demasiado corto");
            else juego.setNombre(nombre);
        }
        juego.setCompName(c.getName());
        juego.setGenero(genero);
        juego.setDescription(description);
        if (lanzamiento.matches("[0-9]+")) {
            if(lanzamiento.length()<=4) {
                if (Integer.parseInt(lanzamiento) <= 2016 && Integer.parseInt(lanzamiento) >= 1952) {
                    juego.setLanzamiento(Integer.parseInt(lanzamiento));

                } else
                    throw new ValidationException("El año no puede ser menor a 1952 o mayor a 2016");
            }else throw new ValidationException("El año es demasiado largo");

        }
        else
            throw new ValidationException("El año debe ser un numero");
        if(Gbpeso.matches("[0.0-9.9]+"))
        juego.setPeso(Double.parseDouble(Gbpeso));
        else {
            throw new ValidationException("el peso debe ser un numero");
        }
        juego.setNomCover(cov);
        juego.setCompany(c);

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

    public void update(String nombre, String genero, String description, String lanzamiento, String Gbpeso)
    {
        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        Juego juego = entityManager.find(Juego.class,nombre);
        juego.setNombre(nombre);
        juego.setGenero(genero);
        juego.setDescription(description);
        if (lanzamiento.matches("[0-9]+"))
            juego.setLanzamiento(Integer.parseInt(lanzamiento));
        else
            throw new ValidationException("Release year isn't a number");
        Double p;
        p = Double.parseDouble(Gbpeso);
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
        //EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
       // entityManager.getTransaction().begin();
        entityManager.persist(juego);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Juego> BuscarGames(String q,String company,String Gender,String ord) {
        TypedQuery<Juego>query = null;
        EntityManager entityManager = DigitalCenterEntityManager.createEntityManager();
        //TypedQuery<Juego> query = entityManager.createQuery("select e from Juego e WHERE lower(e.nombre) like :nombre order by e.nombre", Juego.class);
        if(ord=="Nombre")
            query = entityManager.createQuery("select e from Juego e WHERE lower(e.nombre) like :nombre and lower(e.genero) like :genero and lower(e.compName) like :compName order by e.nombre", Juego.class);
        if(ord=="Genero")
            query = entityManager.createQuery("select e from Juego e WHERE lower(e.nombre) like :nombre and lower(e.genero) like :genero and lower(e.compName) like :compName order by e.genero", Juego.class);
        if(ord=="Compañia")
            query = entityManager.createQuery("select e from Juego e WHERE lower(e.nombre) like :nombre and lower(e.genero) like :genero and lower(e.compName) like :compName order by e.company", Juego.class);
        if(ord=="Lanzamiento")
            query = entityManager.createQuery("select e from Juego e WHERE lower(e.nombre) like :nombre and lower(e.genero) like :genero and lower(e.compName) like :compName order by e.lanzamiento", Juego.class);

        query.setParameter("nombre", "%" + q.toLowerCase() + "%");
        query.setParameter("genero", "%" + Gender.toLowerCase() + "%");
        query.setParameter("compName","%" + company.toLowerCase() + "%");


        //query.setParameter("var", e.ord);



       //TypedQuery<Juego> query2 = entityManager.createQuery("select p from query p order by p.nombre", Juego.class);
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
