package fr.elfoa.drone;

/**
 * @author Pierre Colomb
 */
public class ConsumptionCalculator {


    Integer getConsumption(Integer propeller){
        return 5 * propeller;
    }


    Integer getConsumption(Double distance,Direction direction,Integer weight){
        Integer kgm = (distance.intValue() + 1) * weight;

        switch (direction){
           case HORIZONTAL: return kgm * 7;
           case VERTICAL: return  kgm * 5;
           default: throw new UnsupportedOperationException();
       }
    }

}
