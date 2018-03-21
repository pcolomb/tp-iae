package fr.elfoa.hello.jpa;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author CAMPREDON & CHOMONT
 */

public class CrmJPATest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("crm-database");
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
    public void testClient(){

        Adresse adresse = new Adresse("A",1,"b","a","AZ");
        List<Adresse> listAdresse = new ArrayList<Adresse>();
        listAdresse.add(adresse);
        Client a = new Client("toto@toto.fr","toto","0612847965",listAdresse);

        tx.begin();
        em.persist(a);
        tx.commit();

        Assert.assertEquals(6,a.getId().intValue());
        Assert.assertEquals(7,a.getAdresse().get(0).getId().intValue());
    }

    @Test
    public void testCommande(){

        Item item = new Item("Stylo",10,10);
        Item item2 = new Item("Table",100,500);
        List<Item> items = new ArrayList<Item>();
        items.add(item);
        items.add(item2);
        Adresse adresse = new Adresse("63000",1,"b","a","AZ");
        List<Adresse> listAdresse = new ArrayList<Adresse>();
        listAdresse.add(adresse);
        Client client = new Client("taata@toto.fr","tata","0612847965",listAdresse);

        Commande commande = new Commande(Date.from(Instant.now()),client,client.getAdresse().get(0),items);

        tx.begin();
        em.persist(client);
        em.persist(commande);
        tx.commit();

        Assert.assertEquals(1,client.getId().intValue());
        Assert.assertEquals(2,client.getAdresse().get(0).getId().intValue());
        Assert.assertEquals(3,commande.getId().intValue());
        Assert.assertEquals(4,item.getId().intValue());
    }

    @Test
    public void testResearch() {
        Adresse a = em.find(Adresse.class,1000);

        Assert.assertEquals(1000,a.getId().intValue());
        Assert.assertEquals("lala",a.getVoie());
        Assert.assertEquals("rue",a.getVoieType());

        tx.begin();
        a.setVoie("BUMP");
        tx.commit();

        Assert.assertEquals("BUMP",a.getVoie());

        a = em.find(Adresse.class,1000);

        Assert.assertEquals("BUMP",a.getVoie());
    }

    @Test
    public void testRemove()
    {
        Item i = em.find(Item.class,1001);

        Assert.assertEquals(true,em.contains(i));

        tx.begin();
        em.remove(i);
        tx.commit();

        Assert.assertEquals(false,em.contains(i));

    }
}
