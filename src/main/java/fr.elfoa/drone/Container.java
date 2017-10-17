package fr.elfoa.drone;

import java.util.Set;

/**
 * @author Pierre Colomb
 */
public class Container {

    private static final Integer SIZE = 100;

    private Set<Item> items;


    public void load (Item item){
        items.add(item);
    }

    public Integer getWeight(){
        return items.stream()
                    .mapToInt(Item::getWeight)
                    .sum();
    }


}
