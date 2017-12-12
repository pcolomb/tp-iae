package fr.elfoa.drone;

import javax.inject.Inject;

/**
 * @author Pierre Colomb
 */
public class Module {

    private Integer power = 100;

    Module(){

    }

    Module(BatteryType type){
        switch (type){
            case ION:
                power *= (3/2);
                break;
            case OXYGEN:
                power *= 2;
                break;
        }
    }

    public void use(Integer power){
        this.power -= power;
    }

    public Integer getPower() {
        return power;
    }
}
