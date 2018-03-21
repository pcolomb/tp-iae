package fr.elfoa.drone.propellers;

import fr.elfoa.drone.ConsumptionCalculator;
import fr.elfoa.drone.battery.A_Battery;
import fr.elfoa.drone.battery.IBattery;

import javax.inject.Inject;

@A_Prop_6
public class Propellers_6 extends Propellers implements IPropellers{

    @Inject
    public Propellers_6(){
        number = 6;
    }

    public Propellers_6(@A_Battery IBattery battery){
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
