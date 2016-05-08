package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * EntityManager provider
 */
public class EMFS {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Exams");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void closeFactory(){
        emf.close();
    }
}
