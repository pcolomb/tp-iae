package fr.elfoa.drone;

import fr.elfoa.drone.Qualifiers.LithiumIon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@LithiumIon
public class BatteryLithiumIon implements IBattery
{
    private List<Module> modules = new ArrayList<>();

    public BatteryLithiumIon(){
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

        module.use((power*2)/3);
    }

    @Override
    public Integer getPower(){
        return modules.stream()
                .mapToInt(Module::getPower)
                .sum();
    }
}

