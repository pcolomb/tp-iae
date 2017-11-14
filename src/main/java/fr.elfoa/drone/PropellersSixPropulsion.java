package fr.elfoa.drone;

import fr.elfoa.drone.Qualifiers.LithiumClassic;
import fr.elfoa.drone.Qualifiers.PropellersSix;

import javax.inject.Inject;

@PropellersSix
public class PropellersSixPropulsion implements IPropellers {

    private Integer number = 6;

    @Inject
    @LithiumClassic
    private IBattery battery;

    private ConsumptionCalculator calculator = new ConsumptionCalculator();

    private Boolean isRunning = false;

    @Override
    public void setBattery(Battery battery){
        this.battery = battery;
    }

    @Override
    public void start(){
        battery.use(calculator.getConsumption(number));
        isRunning = true;
    }

    @Override
    public void stop(){
        isRunning = false;
    }

    @Override
    public Integer getNumberOfPropelle() {
        return number;
    }

    @Override
    public Boolean getRunning() {
        return isRunning;
    }
}
