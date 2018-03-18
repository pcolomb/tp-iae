package fr.elfoa.drone;

import fr.elfoa.AbstractBootstraper;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

/**
 * @author Pierre Colomb
 */
public class BatteryTest extends AbstractBootstraper {
    @BeforeClass
    public static void start() {
        init();
    }

    @AfterClass
    public static void stop() {
        shutdown();
    }

    @Inject
    Battery battery;

    @Test(expected = UnsupportedOperationException.class)
    public void use() throws Exception {
        //Battery battery = new Battery();
        battery = getInstance(Battery.class);

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
        Battery battery = new Battery();

        assertEquals(400,battery.getPower().intValue());
    }

}