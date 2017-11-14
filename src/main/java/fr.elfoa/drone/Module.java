package fr.elfoa.drone;

/**
 * @author Pierre Colomb
 */
public class Module {

    public static final int max_power = 100;
    private Integer power = max_power;


    public void use(Integer power) throws Exception {
        if (!gotEnoughPower(power)) {
            //throw new Exception("insufficient power");
        }
        this.power -= power;
    }

    public Integer getPower() {
        return power;
    }

    public boolean gotEnoughPower(int cost) {
        return (this.power - cost) > 0;
    }
}
