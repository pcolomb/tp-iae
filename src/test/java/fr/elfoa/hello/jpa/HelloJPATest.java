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
 * @author Pierre Colomb
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
    public void test(){


        A a = new A("A1","A1");

        tx.begin();
        em.persist(a);
        tx.commit();

        Assert.assertEquals(1,a.getId().intValue());

    }

    @Test
    public void test2(){

        C c = new C("C1","C1");
        B b = new B("B1","B1",c);

        tx.begin();
        em.persist(b);
        tx.commit();

        Assert.assertEquals(2,b.getId().intValue());
        Assert.assertEquals(3,b.getC().getId().intValue());

    }


    @Test
    public void test3(){


        A a = em.find(A.class,1000);

        Assert.assertEquals(1000,a.getId().intValue());
        Assert.assertEquals("A1-1000",a.getA_1());
        Assert.assertEquals("A2-1000",a.getA_2());


        tx.begin();
        a.setA_2("BUMP");
        tx.commit();

        a = em.find(A.class,1000);

        Assert.assertEquals("BUMP",a.getA_2());
    }



    @Test
    public void test4(){


        A a = em.find(A.class,1001);

        em.detach(a);

        a.setA_1("FOO");
        a.setA_2("BAR");

        A freshA = em.find(A.class,1001);

        Assert.assertEquals(1001,a.getId().intValue());
        Assert.assertEquals("A1-1001",freshA.getA_1());
        Assert.assertEquals("A2-1001",freshA.getA_2());


        tx.begin();
        em.merge(a);
        tx.commit();

        a = em.find(A.class,1001);

        Assert.assertEquals("FOO",a.getA_1());
        Assert.assertEquals("BAR",a.getA_2());
    }


    @Test
    public void test5(){


        B b = em.find(B.class,1000);

        Assert.assertEquals(1000,b.getId().intValue());
        Assert.assertEquals("B1-1000",b.getB_1());
        Assert.assertEquals("B2-1000",b.getB_2());

        Assert.assertEquals("B2-1000",b.getB_2());


    }

    @Test
    public void testContains()
    {
        A a = new A("A1","A1");
        A b = new A("A1","A1");
        tx.begin();
        em.persist(a);
        tx.commit();

        Assert.assertEquals(true,em.contains(a));
        Assert.assertEquals(false,em.contains(b));
    }

    @Test
    public void testRemove()
    {
        A a = new A("A1","A1");

        tx.begin();
        em.persist(a);
        tx.commit();

        Assert.assertEquals(true,em.contains(a));
        tx.begin();
        em.remove(a);
        tx.commit();
        Assert.assertEquals(false,em.contains(a));
    }
}