package fr.elfoa.drone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import fr.elfoa.AbstractBootstraper;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.inject.Inject;

public class ContainerTest extends AbstractBootstraper {
    @Inject
    private Container container;

    @BeforeClass
    public static void start() {
        init();
    }

    @AfterClass
    public static void stop() {
        shutdown();
    }

    /*
    // Sans @Inject, sans AbstractBootstraper...
    @Before
    public void setUp() {
        container = new Container();
    }
    */

    @Test
    public void initializedContainer() {
        container = getInstance(Container.class);
        assertEquals(0, container.getWeight().intValue());
        assertEquals(0, container.getSize().intValue());
    }

    @Test
    public void addIntoContainer() {
        container = getInstance(Container.class);
        container.load(new Item(666,42));
        assertEquals(666, container.getSize().intValue());
        assertEquals(42, container.getWeight().intValue());

        container.load(new Item(666, 42));
        assertEquals(1332, container.getSize().intValue());
        assertEquals(84, container.getWeight().intValue());
    }
}
