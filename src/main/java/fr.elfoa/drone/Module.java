package fr.elfoa.drone;

import javax.inject.Inject;
/**
 * @author Pierre Colomb
 */
public class Module {

    private Integer power = 100;

    @Inject
    public Module(){

    }

    public void use(Integer power){
        this.power -= power;
    }

    public Integer getPower() {
        return power;
    }
}
