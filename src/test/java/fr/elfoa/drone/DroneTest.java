package fr.elfoa.drone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import fr.elfoa.AbstractBootstraper;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import javax.inject.Inject;
import javax.swing.*;

/**
 * @author Pierre Colomb
 */
public class DroneTest extends AbstractBootstraper {

    private static final Point ORIGIN = new Point(0d,0d,0d);

    @BeforeClass
    public static void start() {
        init();
    }

    @AfterClass
    public static void stop() {
        shutdown();
    }

    @Inject
    private Drone drone;

    @Test
    public void tackOff() throws Exception {
        Drone drone = getInstance(Drone.class);

        drone.tackOff();

        assertEquals(50d,drone.getCurrentPosition().getAltitude(),0);

    }

    @Test
    public void flyTo() throws Exception {
        Drone drone = getInstance(Drone.class);

        try {
            drone.flyTo(new Point(42.0, 42.0, 42.0));
        } catch (IllegalStateException e) {
            assertTrue(true);
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
        Drone drone = getInstance(Drone.class);
        Point dest = new Point(42.0, 42.0, 50.0);
        Point expected = new Point(42.0, 42.0, 0.0);

        drone.tackOff();
        drone.flyTo(dest);
        drone.landing();

        assertEquals(drone.getCurrentPosition(), expected);
    }



    @Test
    public void isCanFly() throws Exception {
        //Drone drone = new Drone(ORIGIN);
        Drone drone = getInstance(Drone.class);
        assertEquals(drone.isCanFly(), true);
    }



    @Test
    public void getCurrentPosition() throws Exception {
        //Drone drone = new Drone(ORIGIN);
        Drone drone = getInstance(Drone.class);
        Point expected = new Point(42.0, 42.0, 0.0);

        assertEquals(drone.getCurrentPosition(), ORIGIN);

        drone.tackOff();
        drone.flyTo(new Point(42.0, 42.0, 50.0));
        drone.landing();

        assertEquals(drone.getCurrentPosition(), expected);
    }

}