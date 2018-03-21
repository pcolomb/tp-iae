package fr.elfoa.drone.impl;

import fr.elfoa.drone.annotations.QualifierBatteryClassic;
import fr.elfoa.drone.interfaces.IBattery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author Pierre Colomb
 */
@QualifierBatteryClassic
public class Battery implements IBattery {


    private List<Module> modules = new ArrayList<>();

    public Battery(){
        modules = Arrays.asList(new Module(),
                                new Module(),
                                new Module(),
                                new Module());
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
        return modules.stream()
                      .mapToInt(Module::getPower)
                      .sum();
    }

}
