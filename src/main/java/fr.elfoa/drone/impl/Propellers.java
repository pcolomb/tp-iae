package fr.elfoa.drone.impl;

import fr.elfoa.drone.annotations.QualifierBatteryClassic;
import fr.elfoa.drone.annotations.QualifierPropellers4;
import fr.elfoa.drone.impl.ConsumptionCalculator;
import fr.elfoa.drone.interfaces.IBattery;
import fr.elfoa.drone.interfaces.IPropellers;

import javax.inject.Inject;

/**
 * @author Pierre Colomb
 */
@QualifierPropellers4
public class Propellers implements IPropellers{

    private Integer number = 4;

    @Inject
    @QualifierBatteryClassic
    private IBattery battery;

    private ConsumptionCalculator calculator = new ConsumptionCalculator();

    private Boolean isRunning = false;

    public Propellers() {
    }

    public Propellers(IBattery battery){
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
