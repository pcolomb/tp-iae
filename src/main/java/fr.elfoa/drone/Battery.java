package fr.elfoa.drone;

import fr.elfoa.qualifiers.Battery_Standard;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author Pierre Colomb
 */
@Battery_Standard
public class Battery implements IBattery {

    protected List<Module> modules = new ArrayList<>();

    @Inject
    public Battery() {
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
        try {
            module.use(power);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Integer getPower(){
        return modules.stream()
                      .mapToInt(Module::getPower)
                      .sum();
    }

}
