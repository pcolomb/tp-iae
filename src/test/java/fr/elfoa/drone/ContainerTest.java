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

    @Test
    public void initializedContainer() {
        container = getInstance(Container.class);
        assertEquals(0, container.getWeight().intValue());
        assertEquals(0, container.getSize().intValue());
    }

    @Test
    public void addIntoContainer() {
        container = getInstance(Container.class);
        container.load(new Item(42,69));
        assertEquals(42, container.getSize().intValue());
        assertEquals(69, container.getWeight().intValue());

        container.load(new Item(42, 69));
        assertEquals(84, container.getSize().intValue());
        assertEquals(138, container.getWeight().intValue());
    }
}
