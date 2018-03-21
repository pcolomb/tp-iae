package fr.elfoa.drone;

import static org.junit.Assert.assertEquals;

import fr.elfoa.AbstractBootstraper;
import fr.elfoa.drone.impl.Container;
import fr.elfoa.drone.impl.Drone;
import fr.elfoa.drone.impl.Item;
import fr.elfoa.drone.impl.Point;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Pierre Colomb
 */
@RunWith(CdiRunner.class)
public class DroneTest extends AbstractBootstraper{

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
        Point point = new Point(50d, 50d, 50d);

        drone.tackOff();
        drone.flyTo(point);

        assertEquals(50d, drone.getCurrentPosition().getAltitude(), 0);
        assertEquals(50d, drone.getCurrentPosition().getLatitude(),0);
        assertEquals(50d, drone.getCurrentPosition().getLongitude(),0);
    }



    @Test
    public void landing() throws Exception {
        Drone drone = new Drone(ORIGIN);
        Point point = new Point(50d, 50d, 50d);

        drone.tackOff();
        drone.flyTo(point);
        drone.landing();

        assertEquals(0d,drone.getCurrentPosition().getAltitude(),0);
    }



    @Test
    public void isCanFly() throws Exception {

        Drone drone = new Drone(ORIGIN);

        Container container = new Container();
        Item item = new Item(new Integer(10),new Integer(10));

        for(int i = 0; i < 2; ++i) {
            container.load(item);
        }
        drone.addContainers(container);
        assertEquals(Boolean.TRUE,drone.isCanFly());
        drone.addContainers(container);
        assertEquals(Boolean.FALSE,drone.isCanFly());
    }



    @Test
    public void getCurrentPosition() throws Exception {
        Drone drone = new Drone(ORIGIN);
        assertEquals(0d, drone.getCurrentPosition().getAltitude(), 0);
        assertEquals(0d, drone.getCurrentPosition().getLatitude(),0);
        assertEquals(0d, drone.getCurrentPosition().getLongitude(),0);
    }

    @Test
    public void testCDI() throws Exception {
        Drone drone = new Drone();
        assertEquals(Boolean.TRUE,drone.isCanFly());
    }
}