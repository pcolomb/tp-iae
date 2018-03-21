package fr.elfoa.drone;

import fr.elfoa.qualifiers.Battery_Lithium_Ion;

import javax.inject.Inject;
import java.util.Arrays;

@Battery_Lithium_Ion
public class Battery_LithiumIon extends Battery {
    @Inject
    public Battery_LithiumIon() {
        modules = Arrays.asList(new Module(), new Module(), new Module(), new Module(),
                new Module(), new Module());
    }
}
