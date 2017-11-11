package fr.elfoa.drone;

import fr.elfoa.drone.Qualifiers.LithiumClassic;
import fr.elfoa.drone.Qualifiers.LithiumIon;
import fr.elfoa.drone.Qualifiers.LithiumOxygen;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Pierre Colomb
 */
public class Drone {

    @Inject
    @LithiumClassic
    private IBattery battery;

    @Inject
    public Propellers propellers;

    private List<Container> containers;

    @Inject
    private ConsumptionCalculator consumptionCalculator;

    @Inject
    private Point current;

    private Boolean isFlying;

    public Drone()
    {
        this.containers = new ArrayList<>();
        this.isFlying = false;
    }

    public void addContainer(Container container)
    {
        this.containers.add(container);
    }

    public void tackOff()
    {
        if(!isCanFly()){
            return;
        }

        Integer weight = getWeight();

        propellers.start();

        battery.use(consumptionCalculator.getConsumption(50d,Direction.VERTICAL,weight));

        current = new Point(current.getLatitude(),current.getLongitude(),50d);

        isFlying = true;
    }

    public void flyTo(Point point)
    {
        if(!isFlying){
            throw new IllegalStateException();
        }

        double distance = point.distanceTo(current);

        Integer weight = getWeight();

        battery.use(consumptionCalculator.getConsumption(distance,Direction.HORIZONTAL,weight));

        current = point;
    }

    public void landing()
    {
        Integer weight = getWeight();

        battery.use(consumptionCalculator.getConsumption(50d,Direction.VERTICAL,weight));

        current = new Point(current.getLatitude(),current.getLongitude(),0d);

        propellers.stop();
    }

    public boolean isCanFly()
    {
        Integer weight = getWeight();

        return weight == 0 || (weight < propellers.getNumberOfPropelle() * 5);
    }

    public Integer getPower()
    {
        return battery.getPower();
    }

    public Integer getWeight()
    {
        return containers.stream()
                .mapToInt(Container::getWeight)
                .sum();
    }

    public Point getCurrentPosition(){
        return current;
    }

    public void setPoint(Point point){
        this.current = point;
    }
}
