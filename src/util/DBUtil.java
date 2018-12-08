package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
    private final static String PERSISTENCE_UNIT_NAME = "cooking";
    private static EntityManagerFactory emf;

    public static EntityManager createEM() {
        return createEntityManagerFactory().createEntityManager();
    }

    private static EntityManagerFactory createEntityManagerFactory() {
        if(emf==null) {
        emf=Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return emf;
    }

}
