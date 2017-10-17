package fr.elfoa.drone;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * @author Pierre Colomb
 */
public class DroneTest {

    private static final Point ORIGIN = new Point(0d,0d,0d);

    @Test
    public void tackOff() throws Exception {

        Drone drone = new Drone(ORIGIN);

        drone.tackOff();

        assertEquals(50d,drone.getCurrentPosition().getAltitude().intValue());

    }



    @Test
    public void flyTo() throws Exception {

    }



    @Test
    public void landing() throws Exception {

    }



    @Test
    public void isCanFly() throws Exception {

    }



    @Test
    public void getCurrentPosition() throws Exception {

    }

}