package fr.elfoa.drone;

import fr.elfoa.drone.battery.A_Battery;
import fr.elfoa.drone.battery.Battery;
import fr.elfoa.drone.battery.IBattery;
import fr.elfoa.drone.propellers.A_Prop;
import fr.elfoa.drone.propellers.IPropellers;
import fr.elfoa.drone.propellers.Propellers;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Pierre Colomb
 */
public class Drone {

    @Inject
    @A_Battery
    private IBattery battery;

    @Inject
    @A_Prop
    private IPropellers propellers;

    private List<Container> containers;

    @Inject
    private ConsumptionCalculator consumptionCalculator;

    @Inject
    private Point current;

    private Boolean isFlying;


    @Inject
    public Drone(){
        containers = new ArrayList<>();
        this.isFlying = false;
    }

    public Drone(Point current){
        this.current = current;
        this.containers = new ArrayList<>();
        this.isFlying = false;
        this.battery = new Battery();
        this.propellers = new Propellers(battery);

        if(current.getAltitude() != 0){
            throw new IllegalArgumentException();
        }
    }

  /*  public Drone(Point current, IBattery battery){
        this.current = current;
        this.containers = new ArrayList<>();
        this.isFlying = false;
        this.battery = battery;
        this.propellers = new Propellers(battery);

        if(current.getAltitude() != 0){
            throw new IllegalArgumentException();
        }
    }*/

    public void tackOff(){

        if(!isCanFly()){
            return;
        }

        Integer weight = containers.stream()
                                   .mapToInt(Container::getWeight)
                                   .sum();

        propellers.start();

        battery.use(consumptionCalculator.getConsumption(50d,Direction.VERTICAL,weight));

        current = new Point(current.getLatitude(),current.getLongitude(),50d);

        isFlying = true;

    }

    public void flyTo(Point point){

        if(!isFlying){
            throw new IllegalStateException();
        }

        double distance = point.distanceTo(current);

        Integer weight = containers.stream()
                                   .mapToInt(Container::getWeight)
                                   .sum();

        battery.use(consumptionCalculator.getConsumption(distance,Direction.HORIZONTAL,weight));

        current = point;
    }

    public void landing(){

        Integer weight = containers.stream()
                                   .mapToInt(Container::getWeight)
                                   .sum();

        battery.use(consumptionCalculator.getConsumption(50d,Direction.VERTICAL,weight));

        current = new Point(current.getLatitude(),current.getLongitude(),0d);

        propellers.stop();
    }

    public boolean isCanFly(){
        Integer weight = containers.stream()
                                   .mapToInt(Container::getWeight)
                                   .sum();

        return weight == 0 || (weight / propellers.getNumberOfPropelle() * 5) != 0;
    }

    public Point getCurrentPosition(){
        return current;
    }
}
