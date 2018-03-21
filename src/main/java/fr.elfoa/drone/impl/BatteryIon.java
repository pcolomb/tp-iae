package fr.elfoa.drone.impl;

import fr.elfoa.drone.annotations.QualifierBatteryIon;
import fr.elfoa.drone.interfaces.IBattery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// author : CAMPREDON & CHOMONT

//Batterie utilisant module ion 1.5 fois + performante que standard
@QualifierBatteryIon
public class BatteryIon implements IBattery {


    List<Module> modules = new ArrayList<>();


    public BatteryIon(){
        modules = Arrays.asList(new Module(),
                new Module(),
                new Module(),
                new Module(),
                new Module(),
                new Module());
    }

    @Override
    public void use(Integer power) {
        Module module = modules.stream()
                .filter(m -> m.getPower() != 0)
                .findFirst()
                .orElseThrow(UnsupportedOperationException::new);

        module.use(power);
    }

    @Override
    public Integer getPower() {
        return modules.stream()
                .mapToInt(Module::getPower)
                .sum();
    }
}
