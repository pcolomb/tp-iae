package fr.elfoa.drone.battery;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@A_Battery_oxygen
public class Battery_oxygen implements IBattery {

    private List<Module> modules = new ArrayList<>();

    @Inject
    public Battery_oxygen(){
        modules = Arrays.asList(new Module(BatteryType.OXYGEN),
                new Module(BatteryType.OXYGEN),
                new Module(BatteryType.OXYGEN),
                new Module(BatteryType.OXYGEN));
    }

    @Override
    public void use(Integer power){
        Module module = modules.stream()
                .filter(m -> m.getPower() != 0)
                .findFirst()
                .orElseThrow(UnsupportedOperationException::new);

        module.use(power);

    }

    @Override
    public Integer getPower(){
        System.out.println();
        return modules.stream()
                .mapToInt(Module::getPower)
                .sum();
    }
}
