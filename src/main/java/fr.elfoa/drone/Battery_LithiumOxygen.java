package fr.elfoa.drone;

import fr.elfoa.qualifiers.Battery_Lithium_Oxygen;

import javax.inject.Inject;
import java.util.Arrays;

@Battery_Lithium_Oxygen
public class Battery_LithiumOxygen extends Battery {
    @Inject
    public Battery_LithiumOxygen() {
        //2 fois plus de modules que la batterie standard
        modules = Arrays.asList(new Module(), new Module(), new Module(), new Module(),
                new Module(), new Module(), new Module(), new Module());
    }
}
