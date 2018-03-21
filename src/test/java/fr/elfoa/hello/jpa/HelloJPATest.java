package fr.elfoa.hello.jpa;



import fr.elfoa.T2C.Bus;
import fr.elfoa.T2C.Chauffeur;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


/**
 * @author Pierre Colomb
 * Tests pour le TP2
 */
public class HelloJPATest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello-database");
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
    public void persist() {
        Bus bus = new Bus("ZZ3");
        tx.begin();
        em.persist(bus);
        tx.commit();

        Assert.assertEquals(1, bus.getId().intValue());
    }

    @Test
    public void cascadePersist() {
        Chauffeur chauffeur = new Chauffeur("Gerard le vicelard", 21);
        chauffeur.setBus(new Bus("ZZ3"));
        tx.begin();
        em.persist(chauffeur);
        tx.commit();

        Assert.assertEquals(2, chauffeur.getId().intValue());
        Assert.assertEquals(3, chauffeur.getBus().getId().intValue());
    }
}