package fr.elfoa.drone;

import javax.inject.Inject;

/**
 * @author Pierre Colomb
 */
public class Propellers implements IPropellers {

    private Integer number = 4;

    @Inject
    private Battery battery;

    @Inject
    private ConsumptionCalculator calculator ;

    private Boolean isRunning = false;

    @Inject
    public Propellers() {}

    @Override
    public void start(){
        battery.use(calculator.getConsumption(number));
        isRunning = true;
    }

    @Override
    public void stop(){
        isRunning = false;
    }


    public Integer getNumberOfPropeller() {
        return number;
    }



    public Boolean getRunning() {
        return isRunning;
    }

    public void setBattery(Battery battery) {
        this.battery = battery;
    }
}
