package fr.elfoa.drone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ItemTest {
    private Item item;

    @Before
    public void setUp() {
        item = new Item();
    }

    @Test
    public void initializedItem() {
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
