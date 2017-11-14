package fr.elfoa.drone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ContainerTest {
    private Container container;

    @Before
    public void setUp() {
        container = new Container();
    }

    @Test
    public void initializedContainer() {
        assertEquals(0, container.getWeight().intValue());
        assertEquals(0, container.getSize().intValue());
    }

    @Test
    public void addIntoContainer() {
        container.load(new Item(666,42));
        assertEquals(666, container.getSize().intValue());
        assertEquals(42, container.getWeight().intValue());

        container.load(new Item(666, 42));
        assertEquals(1332, container.getSize().intValue());
        assertEquals(84, container.getWeight().intValue());
    }
}
