package fr.elfoa.drone;

import fr.elfoa.drone.Qualifiers.LithiumOxygen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@LithiumOxygen
public class BatteryLithiumOxygen implements IBattery
{
    private List<Module> modules = new ArrayList<>();

    public BatteryLithiumOxygen(){
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

        module.use(power/2);
    }

    @Override
    public Integer getPower(){
        return modules.stream()
                .mapToInt(Module::getPower)
                .sum();
    }
}