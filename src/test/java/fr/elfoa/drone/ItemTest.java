package fr.elfoa.drone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import fr.elfoa.AbstractBootstraper;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.inject.Inject;

public class ItemTest extends AbstractBootstraper {
    @Inject
    private Item item;

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
        item = new Item();
    }
    */

    @Test
    public void initializedItem() {
        item = getInstance(Item.class);
        assertEquals(0, item.getWeight().intValue());
        assertEquals(0, item.getSize().intValue());
    }

    @Test
    public void initalizedItemWithParam() {
        item = new Item(42, 666);
        assertEquals(666, item.getWeight().intValue());
        assertEquals(42, item.getSize().intValue());
    }
}
