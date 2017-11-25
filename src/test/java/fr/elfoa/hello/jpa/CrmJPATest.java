package fr.elfoa.hello.jpa;



import org.junit.*;
import org.junit.runners.MethodSorters;

import javax.persistence.*;
import java.util.List;


/**
 * @author Pierre Colomb
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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

    // -----------------------------------------------------------------
    // CLIENT

    @Test
    public void test1_saveClient()
    {
        Client clientToSave = new Client("Nom 1", "Prenom 1", "nom1.prenom1@mail.fr", "0786000000");
        Adresse adresse1 = new Adresse("63000", 12, "France", "Gergovia", "Boulevard");
        Adresse adresse2 = new Adresse("12345", 6, "Australie", "Uluru", "Road");
        clientToSave.addAdresse(adresse1);
        clientToSave.addAdresse(adresse2);

        //displayDataFromClient();

        tx.begin();
        em.persist(clientToSave);
        tx.commit();

        Assert.assertEquals(1,clientToSave.getId().intValue());
        Assert.assertEquals(2,clientToSave.getAdresses().size());
        Assert.assertEquals(2,clientToSave.getAdresses().get(0).getId().intValue());
        Assert.assertEquals(3,clientToSave.getAdresses().get(1).getId().intValue());

        //displayDataFromClient();
    }

    @Test
    public void test2_readClient()
    {
        Client clientToRead = em.find(Client.class, 1);

        Assert.assertEquals("Nom 1", clientToRead.getNom());
        Assert.assertEquals("Prenom 1", clientToRead.getPrenom());
        Assert.assertEquals("nom1.prenom1@mail.fr", clientToRead.getMail());
        Assert.assertEquals("0786000000", clientToRead.getTelephone());

        Assert.assertEquals(2,clientToRead.getAdresses().size());
        Assert.assertEquals("France",clientToRead.getAdresses().get(0).getPays());
        Assert.assertEquals("Uluru",clientToRead.getAdresses().get(1).getVoie());
    }

    @Test
    public void test3_updateClient()
    {
        Client clientToUpdate = em.find(Client.class, 1);

        Assert.assertEquals("Nom 1", clientToUpdate.getNom());
        Assert.assertEquals(2,clientToUpdate.getAdresses().size());

        tx.begin();
        clientToUpdate.setNom("MacBernik");
        clientToUpdate.setPrenom("Victor");
        clientToUpdate.getAdresses().remove(1);
        clientToUpdate.getAdresses().get(0).setPays("Ile de la tortue");
        tx.commit();

        Client clientToRead = em.find(Client.class, 1);
        Assert.assertEquals("MacBernik", clientToRead.getNom());
        Assert.assertEquals("Victor", clientToRead.getPrenom());
        Assert.assertEquals(1, clientToRead.getAdresses().size());
        Assert.assertEquals("Ile de la tortue", clientToRead.getAdresses().get(0).getPays());
    }

    @Test
    public void test4_updateClientWithDetach()
    {
        Client clientToUpdate = em.find(Client.class, 1);
        Assert.assertEquals("MacBernik", clientToUpdate.getNom());

        em.detach(clientToUpdate);
        clientToUpdate.setNom("Machicouli");

        Client clientToReadBeforeMerge = em.find(Client.class, 1);
        Assert.assertEquals("MacBernik", clientToReadBeforeMerge.getNom());

        tx.begin();
        em.merge(clientToUpdate);
        tx.commit();

        Client clientToReadAfterMerge = em.find(Client.class, 1);
        Assert.assertEquals("Machicouli", clientToReadAfterMerge.getNom());
    }

    // -----------------------------------------------------------------
    // ITEM

    @Test
    public void test5_saveItem()
    {
        Item item = new Item("PS4", 30, 2);

        tx.begin();
        em.persist(item);
        tx.commit();

        Assert.assertEquals(4,item.getId().intValue());
    }

    @Test
    public void test6_readItem()
    {
        Item itemToRead = em.find(Item.class, 4);

        Assert.assertEquals("PS4",itemToRead.getLabel());
        Assert.assertEquals(30,itemToRead.getTaille().intValue());
        Assert.assertEquals(2,itemToRead.getPoids().intValue());
    }

    @Test
    public void test7_updateItem()
    {
        Item itemToUpdate = em.find(Item.class, 4);

        Assert.assertEquals("PS4",itemToUpdate.getLabel());

        tx.begin();
        itemToUpdate.setLabel("Switch");
        itemToUpdate.setTaille(50);
        itemToUpdate.setPoids(5);
        tx.commit();

        Item itemToRead = em.find(Item.class, 4);
        Assert.assertEquals("Switch",itemToRead.getLabel());
        Assert.assertEquals(50,itemToRead.getTaille().intValue());
        Assert.assertEquals(5,itemToRead.getPoids().intValue());
    }

    @Test
    public void test8_updateItemWithDetach()
    {
        Item itemToUpdate = em.find(Item.class, 4);
        Assert.assertEquals("Switch",itemToUpdate.getLabel());

        em.detach(itemToUpdate);
        itemToUpdate.setLabel("XBox");

        Item itemToReadBeforeMerge = em.find(Item.class, 4);
        Assert.assertEquals("Switch", itemToReadBeforeMerge.getLabel());

        tx.begin();
        em.merge(itemToUpdate);
        tx.commit();

        Item itemToReadAfterMerge = em.find(Item.class, 4);
        Assert.assertEquals("XBox", itemToReadAfterMerge.getLabel());
    }

    // -----------------------------------------------------------------
    // Requetes d'affichage d'une table

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
