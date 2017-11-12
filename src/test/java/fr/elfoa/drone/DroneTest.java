package fr.elfoa.drone;

import static org.junit.Assert.assertEquals;

import fr.elfoa.AbstractBootstraper;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Pierre Colomb
 */
public class DroneTest extends AbstractBootstraper {

    private static final Point ORIGIN = new Point(0d,0d,0d);

    @Test
    public void tackOffDroneMoved() throws Exception
    {
        Drone drone = getInstance(Drone.class);
        drone.setPoint(ORIGIN);

        drone.tackOff();

        assertEquals(new Point(0.0,0.0,50.0),drone.getCurrentPosition());
    }



    @Test
    public void flyTo() throws Exception {
        Drone drone = getInstance(Drone.class);
        drone.setPoint(ORIGIN);

        assertEquals(ORIGIN, drone.getCurrentPosition());

        drone.tackOff();
        Point destination = new Point(10d,10d,10d);
        drone.flyTo(destination);

        assertEquals(destination, drone.getCurrentPosition());
    }



    @Test
    public void landing() throws Exception {
        Drone drone = getInstance(Drone.class);
        drone.setPoint(ORIGIN);

        drone.tackOff();
        Point destination = new Point(10d,10d,10d);
        drone.flyTo(destination);

        drone.landing();
        assertEquals(new Point(10.0,10.0,00.0),drone.getCurrentPosition());
    }



    @Test
    public void isCanFly() throws Exception {
        Drone drone = getInstance(Drone.class);
        drone.setPoint(ORIGIN);

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
        Drone drone = getInstance(Drone.class);
        drone.setPoint(ORIGIN);

        drone.tackOff();
        Point destination = new Point(10d,10d,10d);
        drone.flyTo(destination);

        drone.landing();
        assertEquals(new Point(10.0,10.0,00.0),drone.getCurrentPosition());
    }

    //=============== TESTS FOR BATTERY QUALIFIERS

    @Test
    public void DroneBatteryDecreased_BatteryClassic()
    {
        Drone drone = getInstance(Drone.class);
        drone.setPoint(ORIGIN);
        Integer remainingPower = 400;

        assertEquals(remainingPower, drone.getPower(), 0);

        drone.tackOff(); //use 50 power
        remainingPower -= 50;

        assertEquals(remainingPower, drone.getPower(), 0);

        Point destination = new Point(10d,10d,10d);
        remainingPower -= destination.distanceTo(drone.getCurrentPosition()).intValue() + drone.getWeight();
        drone.flyTo(destination);

        assertEquals(remainingPower, drone.getPower(), 0);

        drone.landing();
        remainingPower -= 50;

        assertEquals(remainingPower, drone.getPower(), 0);
    }

    /*
    @Test
    public void DroneBatteryDecreased_BatteryLithiumIon()
    {
        Drone drone = getInstance(Drone.class);
        drone.setPoint(ORIGIN);
        Integer remainingPower = 400;

        assertEquals(remainingPower, drone.getPower(), 0);

        drone.tackOff(); //use 50 power
        remainingPower -= (50*2)/3;

        assertEquals(remainingPower, drone.getPower(), 0);

        Point destination = new Point(10d,10d,10d);
        remainingPower -= ((destination.distanceTo(drone.getCurrentPosition()).intValue() + drone.getWeight())*2)/3;
        drone.flyTo(destination);

        assertEquals(remainingPower, drone.getPower(), 0);

        drone.landing();
        remainingPower -= (50*2)/3;

        assertEquals(remainingPower, drone.getPower(), 0);
    }

    @Test
    public void DroneBatteryDecreased_BatteryLithiumOxygen()
    {
        Drone drone = getInstance(Drone.class);
        drone.setPoint(ORIGIN);
        Integer remainingPower = 400;

        assertEquals(remainingPower, drone.getPower(), 0);

        drone.tackOff(); //use 50 power
        remainingPower -= 50/2;

        assertEquals(remainingPower, drone.getPower(), 0);

        Point destination = new Point(10d,10d,10d);
        remainingPower -= (destination.distanceTo(drone.getCurrentPosition()).intValue() + drone.getWeight())/2;
        drone.flyTo(destination);

        assertEquals(remainingPower, drone.getPower(), 0);

        drone.landing();
        remainingPower -= 50/2;

        assertEquals(remainingPower, drone.getPower(), 0);
    }*/

    @BeforeClass
    public static void start(){
        init();
    }

    @AfterClass
    public static void stop(){
        shutdown();
    }
}