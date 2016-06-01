package bo.edu.ucbcba.group5.controller;

import bo.edu.ucbcba.group5.dao.DigitalCenterEntityManager;
import bo.edu.ucbcba.group5.exceptions.ValidationException;
import bo.edu.ucbcba.group5.model.Company;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Abel on 5/28/2016.
 */
public class CompanyController {

    public void saveCompany(String Name, String Anio, String osc) {
        EntityManager em = DigitalCenterEntityManager.createEntityManager();
        em.getTransaction().begin();
        Company director = new Company();
        if (Name.isEmpty()){
            throw new ValidationException("debe ingresar un nombre");

        }
        if (Anio.isEmpty()){
            throw new ValidationException("debe ingresar un año");
        }
        if (osc.isEmpty()){
            throw new ValidationException("debe ingresar un numero de premios");
        }
        //if (address.isEmpty()){
       //     throw new ValidationException("Address can't be blank");
       // }
        int length;
        length = Name.length();
        if (length > 100)
            throw new ValidationException("el nombre es demasiado largo");
        else {
            director.setName(Name);
        }
        director.setName(Name);
        if(osc.matches("[0-9]+")) {
            if(osc.length()<=3)
            director.setAwards(Integer.parseInt(osc));
            else
                throw new ValidationException("la cantidad de premios no puede ser tanta ");

        }
        else {
            throw new ValidationException("la cantidad de premios debe ser un numero ");
        }

        if (Anio.matches("[0-9]+")) {
            {
                if(Anio.length()<=4) {
                    if (Integer.parseInt(Anio) <= 2016 && Integer.parseInt(Anio) >= 1952) {
                        director.setAnio(Integer.parseInt(Anio));

                    } else
                        throw new ValidationException("El año no puede ser menor a 1952 o mayor a 2016");
                }
                else throw new ValidationException("El año es demasiado largo");
            }
        }else {
            throw new ValidationException("el año no es un numero");
        }
        em.persist(director);
        em.getTransaction().commit();
        em.close();
    }

    public List<Company> getAllCompanys() {
        EntityManager em = DigitalCenterEntityManager.createEntityManager();
        TypedQuery<Company> query = em.createQuery("select d from Company d order by d.Name", Company.class);
        List<Company> list = query.getResultList();
        em.close();
        return list;
    }
}
