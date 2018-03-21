package fr.elfoa.drone.propellers;

import fr.elfoa.drone.ConsumptionCalculator;
import fr.elfoa.drone.battery.A_Battery;
import fr.elfoa.drone.battery.IBattery;

import javax.inject.Inject;

/**
 * @author Pierre Colomb
 */
@A_Prop
public class Propellers implements IPropellers {

    protected Integer number = 4;

    @Inject
    @A_Battery
    protected IBattery battery;

    @Inject
    protected ConsumptionCalculator calculator;

    protected Boolean isRunning = false;

    @Inject
    public Propellers(){

    }

    public Propellers(@A_Battery IBattery battery){
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
