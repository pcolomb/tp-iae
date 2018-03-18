package fr.elfoa.drone;

import com.sun.management.VMOption;
import fr.elfoa.hello.jpa.C;
import org.omg.PortableInterceptor.ObjectReferenceFactory;

import javax.annotation.PostConstruct;
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

    private List<Container> containers = new ArrayList<Container>();

    @Inject
    private ConsumptionCalculator consumptionCalculator; // = new ConsumptionCalculator();

    @Inject
    private Point current;

    private Boolean isFlying;

    @Inject
    public Drone() {
        this.current = new Point(0.0,0.0,0.0);
        this.isFlying = false;
    }

    public Drone(Point pt) {
        this.current = pt;
        this.isFlying = this.current.getAltitude() > 0;
    }

//    @PostConstruct
//    public void Init() {
//        this.propellers.setBattery(this.battery);
//    }

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
