package fr.elfoa.drone;

import fr.elfoa.AbstractBootstraper;
import fr.elfoa.qualifiers.Battery_Standard;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

/**
 * @author Pierre Colomb
 */
public class BatteryTest extends AbstractBootstraper {
    @Inject
    private Battery battery;

    @Before
    public void setUp() {
        battery = new Battery();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void use() throws Exception {
        for(int i = 0 ; i < 7  ; i++) {
            battery.use(50);
        }

        assertEquals(50,battery.getPower().intValue());

        battery.use(50);

        assertEquals(0,battery.getPower().intValue());

        battery.use(1);

    }



    @Test
    public void getPower() throws Exception {
        assertEquals(400,battery.getPower().intValue());
    }

}