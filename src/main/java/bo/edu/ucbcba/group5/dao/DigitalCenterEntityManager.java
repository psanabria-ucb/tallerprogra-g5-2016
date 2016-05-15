package bo.edu.ucbcba.group5.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// This is a singleton that give us a entity manager to manage
// in the entire DAO
public class DigitalCenterEntityManager {
    private static DigitalCenterEntityManager entityManager;

    private EntityManagerFactory entityManagerFactory;

    private DigitalCenterEntityManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory("DigitalCenter");
    }

    private static DigitalCenterEntityManager getInstance() {
        if (entityManager == null)
            entityManager = new DigitalCenterEntityManager();
        return entityManager;
    }

    public static EntityManager createEntityManager() {
        return getInstance().entityManagerFactory.createEntityManager();
    }
}
