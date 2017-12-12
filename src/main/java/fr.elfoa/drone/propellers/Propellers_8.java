package fr.elfoa.drone.propellers;

import fr.elfoa.drone.ConsumptionCalculator;
import fr.elfoa.drone.battery.A_Battery;
import fr.elfoa.drone.battery.IBattery;

import javax.inject.Inject;

@A_Prop_8
public class Propellers_8 extends Propellers implements IPropellers{

    @Inject
    public Propellers_8(){
        number = 8;
    }

    public Propellers_8(@A_Battery IBattery battery){
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
