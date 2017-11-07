package fr.elfoa.drone;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Pierre Colomb
 */
public class Drone {

    @Inject
    private Battery battery;

    @Inject
    private Propellers propellers;

    @Inject
    private List<Container> containers;

    @Inject
    private ConsumptionCalculator consumptionCalculator = new ConsumptionCalculator();

    @Inject
    private Point current;

    private Boolean isFlying;


    public Drone(Point current){
        this.containers = new ArrayList<>();
        this.current = current;
        this.battery = new Battery();
        this.propellers = new Propellers(battery);
        this.isFlying = false;

        if(current.getAltitude() != 0){
            throw new IllegalArgumentException();
        }


    }

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
