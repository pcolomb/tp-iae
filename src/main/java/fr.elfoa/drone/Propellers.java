package fr.elfoa.drone;

import javax.inject.Inject;

/**
 * @author Pierre Colomb
 */
public class Propellers {

    private Integer number = 4;

    @Inject
    private Battery battery;

    @Inject
    private ConsumptionCalculator calculator ;

    private Boolean isRunning = false;

    @Inject
    public Propellers() {}

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

    public void setBattery(Battery battery) {
        this.battery = battery;
    }
}
