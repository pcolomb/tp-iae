package fr.elfoa.drone;

import java.util.List;


/**
 * @author Pierre Colomb
 */
public class Drone {

    private Battery battery;

    private Propellers propellers;

    private List<Container> containers;

    private ConsumptionCalculator consumptionCalculator = new ConsumptionCalculator();

    private Point current;

    private Boolean isFlying;


    public Drone(Point current){
        this.current = current;
        this.battery = new Battery();
        this.propellers = new Propellers(battery);

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
