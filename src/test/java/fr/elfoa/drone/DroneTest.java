package fr.elfoa.drone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import javax.swing.*;

/**
 * @author Pierre Colomb
 */
public class DroneTest {

    private static final Point ORIGIN = new Point(0d,0d,0d);

    @Test
    public void tackOff() throws Exception {

        Drone drone = new Drone(ORIGIN);

        drone.tackOff();

        assertEquals(50d,drone.getCurrentPosition().getAltitude(),0);

    }

    @Test
    public void flyTo() throws Exception {
        Drone drone = new Drone(ORIGIN);

        try {
            drone.flyTo(new Point(42.0, 42.0, 42.0));
        } catch (IllegalStateException e) {
            assertTrue(true);
        } catch (Exception ex) {
            fail();
        }

        drone.tackOff();
        try {
            Point dest = new Point(42.0, 42.0, 42.0);
            drone.flyTo(dest);
            assertEquals(drone.getCurrentPosition(), dest);
        } catch (IllegalStateException e) {
            fail();
        }
    }



    @Test
    public void landing() throws Exception {
        Drone drone = new Drone(ORIGIN);
        Point dest = new Point(42.0, 42.0, 50.0);
        Point expected = new Point(42.0, 42.0, 0.0);

        drone.tackOff();
        drone.flyTo(dest);
        drone.landing();

        assertEquals(drone.getCurrentPosition(), expected);
    }



    @Test
    public void isCanFly() throws Exception {
        Drone drone = new Drone(ORIGIN);
        assertEquals(drone.isCanFly(), true);
    }



    @Test
    public void getCurrentPosition() throws Exception {
        Drone drone = new Drone(ORIGIN);
        Point expected = new Point(42.0, 42.0, 0.0);

        assertEquals(drone.getCurrentPosition(), ORIGIN);

        drone.tackOff();
        drone.flyTo(new Point(42.0, 42.0, 50.0));
        drone.landing();

        assertEquals(drone.getCurrentPosition(), expected);
    }

}