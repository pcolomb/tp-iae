package fr.elfoa.hello.jpa;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author CAMPREDON & CHOMONT
 */

public class CrmJPATest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("crm--database");
    private EntityManager em;
    private EntityTransaction tx;

    @Before
    public void initEntityManager() throws Exception {
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }


    @After
    public void closeEntityManager() throws Exception {
        if (em != null) {
            em.close();
        }
    }

    @Test
    public void test(){


        A a = new A("A1","A1");

        tx.begin();
        em.persist(a);
        tx.commit();

        Assert.assertEquals(1,a.getId().intValue());

    }
}
