package fr.elfoa.drone;

import static org.junit.Assert.assertEquals;

import fr.elfoa.AbstractBootstraper;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Pierre Colomb
 */
public class DroneTest extends AbstractBootstraper {

    private static final Point ORIGIN = new Point(0d,0d,0d);

    @BeforeClass
    public static void start(){
        init();
    }

    @Test
    public void tackOff() throws Exception {
        Drone drone = getInstance(Drone.class);
        drone.tackOff();
        assertEquals(50d,drone.getCurrentPosition().getAltitude(),0);
    }



    @Test
    public void flyTo() throws Exception {
        Drone bob = getInstance(Drone.class);
        bob.tackOff();
        bob.flyTo(new Point(1d,1d,1d));
        assertEquals(1d, bob.getCurrentPosition().getAltitude(),0);
        assertEquals(1d, bob.getCurrentPosition().getLongitude(),0);
        assertEquals(1d, bob.getCurrentPosition().getLatitude(),0);
    }



    @Test
    public void landing() throws Exception {
        Drone bob = getInstance(Drone.class);
        bob.tackOff();
        bob.flyTo(new Point(1d,1d,1d));
        bob.landing();
        assertEquals(0d, bob.getCurrentPosition().getAltitude(),0);
        assertEquals(1d, bob.getCurrentPosition().getLongitude(),0);
        assertEquals(1d, bob.getCurrentPosition().getLatitude(),0);
    }



    @Test
    public void isCanFly() throws Exception {
        Drone bob = getInstance(Drone.class);
        assertEquals(true, bob.isCanFly());
    }



    @Test
    public void getCurrentPosition() throws Exception {
        Drone bob = getInstance(Drone.class);
        Point tmp = new Point(0d, 0d, 0d);
        assertEquals(tmp.getAltitude(), bob.getCurrentPosition().getAltitude(),0);
        assertEquals(tmp.getLongitude(), bob.getCurrentPosition().getLongitude(),0);
        assertEquals(tmp.getLatitude(), bob.getCurrentPosition().getLatitude(),0);

        bob.tackOff();
        bob.flyTo(new Point(1d,10d,12d));
        bob.landing();
        Point tmp1 = new Point(1d, 10d, 0d);
        assertEquals(tmp1.getAltitude(), bob.getCurrentPosition().getAltitude(),0);
        assertEquals(tmp1.getLongitude(), bob.getCurrentPosition().getLongitude(),0);
        assertEquals(tmp1.getLatitude(), bob.getCurrentPosition().getLatitude(),0);
    }

    @AfterClass
    public static void stop(){
        shutdown();
    }
}