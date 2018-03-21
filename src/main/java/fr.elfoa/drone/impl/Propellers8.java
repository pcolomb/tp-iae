package fr.elfoa.drone.impl;

import fr.elfoa.drone.annotations.QualifierBatteryClassic;
import fr.elfoa.drone.annotations.QualifierPropellers6;
import fr.elfoa.drone.annotations.QualifierPropellers8;
import fr.elfoa.drone.impl.ConsumptionCalculator;
import fr.elfoa.drone.interfaces.IBattery;
import fr.elfoa.drone.interfaces.IPropellers;

import javax.inject.Inject;

/**
 * @author CAMPREDON & CHOMONT
 */
@QualifierPropellers8
public class Propellers8 implements IPropellers{

    private Integer number = 8;

    @Inject
    @QualifierBatteryClassic
    private IBattery battery;

    private ConsumptionCalculator calculator = new ConsumptionCalculator();

    private Boolean isRunning = false;

    public Propellers8() {
    }

    public Propellers8(IBattery battery){
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
