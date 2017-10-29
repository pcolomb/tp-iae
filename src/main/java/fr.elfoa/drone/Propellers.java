package fr.elfoa.drone;

import javax.inject.Inject;

/**
 * @author Pierre Colomb
 */
public class Propellers {

    private Integer number = 4;

    @Inject
    private Battery battery;

    private ConsumptionCalculator calculator = new ConsumptionCalculator();

    private Boolean isRunning = false;

    public void setBattery(Battery battery){
        this.battery = battery;
    }

    public void start(){
        battery.use(calculator.getConsumption(number));
        isRunning = true;
    }

    public void stop(){
        isRunning = false;
    }


    public Integer getNumberOfPropelle() {
        return number;
    }



    public Boolean getRunning() {
        return isRunning;
    }
}
