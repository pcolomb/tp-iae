package fr.elfoa.drone;

import fr.elfoa.drone.battery.Battery;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Pierre Colomb
 */
public class BatteryTest {

    @Test(expected = UnsupportedOperationException.class)
    public void use() throws Exception {
        Battery battery = new Battery();

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