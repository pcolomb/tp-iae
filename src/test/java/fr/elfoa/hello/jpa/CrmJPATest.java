package fr.elfoa.hello.jpa;



import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;


/**
 * @author Pierre Colomb
 */
public class CrmJPATest
{
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("crm-database");
    private EntityManager em;
    private EntityTransaction tx;


    @Before
    public void initEntityManager() throws Exception
    {
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }


    @After
    public void closeEntityManager() throws Exception
    {
        if (em != null) {
            em.close();
        }
    }

    @Test
    public void testPersistClient()
    {
        Client client = new Client("Nom 1", "Prenom 1", "nom1.prenom1@mail.fr", "0786000000");
        Adresse adresse1 = new Adresse("63000", 12, "France", "Gergovia", "Boulevard");
        client.addAdresse(adresse1);

        displayDataFromClient();

        tx.begin();
        em.persist(client);
        tx.commit();

        Assert.assertEquals(1,client.getId().intValue());

        displayDataFromClient();
    }

    @Test
    public void testPersistAdresse()
    {
        Adresse adresse = new Adresse("63000", 12, "France", "Gergovia", "Boulevard");

        tx.begin();
        em.persist(adresse);
        tx.commit();

        Assert.assertEquals(1,adresse.getId().intValue());

        displayDataFromAdresse();
    }

    @Test
    public void testPersistItem()
    {
        Item item = new Item("PS4", 30, 2);

        tx.begin();
        em.persist(item);
        tx.commit();

        Assert.assertEquals(1,item.getId().intValue());

        displayDataFromItem();
    }

    private Integer displayDataFromClient()
    {
        Query q = em.createQuery("select client from Client client");
        List<Client> results = q.getResultList();
        for (Client client : results) {
            System.out.println(client.toString());
        }
        System.out.println("Size: " + results.size());
        return results.size();
    }

    private Integer displayDataFromAdresse()
    {
        Query q = em.createQuery("select adresse from Adresse adresse");
        List<Adresse> results = q.getResultList();
        for (Adresse adresse : results) {
            System.out.println(adresse.toString());
        }
        System.out.println("Size: " + results.size());
        return results.size();
    }

    private Integer displayDataFromItem()
    {
        Query q = em.createQuery("select item from Item item");
        List<Item> results = q.getResultList();
        for (Item item : results) {
            System.out.println(item.toString());
        }
        System.out.println("Size: " + results.size());
        return results.size();
    }
}
