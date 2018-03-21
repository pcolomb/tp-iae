package fr.elfoa.drone;

import javax.inject.Inject;

/**
 * @author Pierre Colomb
 */
public class ConsumptionCalculator {


    Integer getConsumption(Integer propeller){
        return 0;
    }


    Integer getConsumption(Double distance,Direction direction,Integer weight){
        return 0;
    }

    @Inject
    public ConsumptionCalculator() {}

}
