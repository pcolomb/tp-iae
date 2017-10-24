package fr.elfoa.drone;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * @author Pierre Colomb
 */
public class DroneTest {

    private static final Point ORIGIN = new Point(0d,0d,0d);

    @Test
    public void tackOff() throws Exception
    {
        Drone drone = new Drone(ORIGIN);

        drone.tackOff();

        assertEquals(new Point(0.0,0.0,50.0),drone.getCurrentPosition());
    }

    @Test
    public void flyTo() throws Exception {
        Drone drone = new Drone(ORIGIN);

        assertEquals(ORIGIN, drone.getCurrentPosition());

        drone.tackOff();
        Point destination = new Point(10d,10d,10d);
        drone.flyTo(destination);

        assertEquals(destination, drone.getCurrentPosition());
    }



    @Test
    public void landing() throws Exception {
        Drone drone = new Drone(ORIGIN);

        drone.tackOff();
        Point destination = new Point(10d,10d,10d);
        drone.flyTo(destination);

        drone.landing();
        assertEquals(new Point(10.0,10.0,00.0),drone.getCurrentPosition());
    }



    @Test
    public void isCanFly() throws Exception {
        Drone drone = new Drone(ORIGIN);

        assertEquals(drone.isCanFly(), true);

        Container container1 = new Container();
        container1.load(new Item(10,10));
        drone.addContainer(container1);

        assertEquals(drone.isCanFly(), true);

        Container container2 = new Container();
        container2.load(new Item(100,10));
        drone.addContainer(container2);

        assertEquals(drone.isCanFly(), false);
    }



    @Test
    public void getCurrentPosition() throws Exception {
        Drone drone = new Drone(ORIGIN);

        drone.tackOff();
        Point destination = new Point(10d,10d,10d);
        drone.flyTo(destination);

        drone.landing();
        assertEquals(new Point(10.0,10.0,00.0),drone.getCurrentPosition());
    }
}