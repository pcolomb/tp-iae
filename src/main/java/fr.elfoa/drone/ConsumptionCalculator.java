package fr.elfoa.drone;

/**
 * @author Pierre Colomb
 */
public class ConsumptionCalculator
{
    Integer getConsumption(Integer propeller)
    {
        return propeller*10;
    }

    Integer getConsumption(Double distance,Direction direction,Integer weight)
    {
        return distance.intValue() + weight;
    }
}
