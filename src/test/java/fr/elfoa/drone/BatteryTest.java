package fr.elfoa.drone;

import fr.elfoa.drone.annotations.QualifierBatteryOxygen;
import fr.elfoa.drone.impl.Battery;
import fr.elfoa.drone.impl.BatteryIon;
import fr.elfoa.drone.interfaces.IBattery;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

/**
 * @author Pierre Colomb
 */
@RunWith(CdiRunner.class)
public class BatteryTest {

    @Inject
    @QualifierBatteryOxygen
    IBattery batteryOxygen;

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

    @Test
    public void getPowerIon() throws Exception {
        BatteryIon battery = new BatteryIon();

        assertEquals(600,battery.getPower().intValue());
    }

    @Test
    public void getPowerOxygen() throws Exception {

        assertEquals(800,batteryOxygen.getPower().intValue());
    }

}