package fr.elfoa.drone.impl;

import fr.elfoa.drone.annotations.QualifierBatteryOxygen;
import fr.elfoa.drone.interfaces.IBattery;

import javax.enterprise.inject.Model;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// author : CAMPREDON & CHOMONT

//Batterie utilisant module Lithium-oxygen deux fois + performante que standard

@QualifierBatteryOxygen
public class BatteryOxygen implements IBattery {


    List<Module> modules = new ArrayList<>();

    public BatteryOxygen(){
        modules = Arrays.asList(new Module(),
                new Module(),
                new Module(),
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
