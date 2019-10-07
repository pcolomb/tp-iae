package fr.elfoa.drone;

import static org.junit.Assert.assertEquals;

import fr.elfoa.AbstractBootstraper;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Pierre Colomb
 */
public class DroneTest extends AbstractBootstraper{

    private static final Point ORIGIN = new Point(0d,0d,0d);

    @BeforeClass
    public static void start(){
        init();
    }

    @Test
    public void tackOff() throws Exception {

        Drone drone = new Drone(ORIGIN);

        drone.tackOff();

        assertEquals(50d,drone.getCurrentPosition().getAltitude(),0);



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

    @AfterClass
    public static void stop(){
        shutdown();
    }
}