package fr.elfoa.drone.impl;

/**
 * @author Pierre Colomb
 */
public class Module {

    private Integer power = 100;


    public void use(Integer power){
        this.power -= power;
    }

    public Integer getPower() {
        return power;
    }
}
