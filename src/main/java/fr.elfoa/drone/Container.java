package fr.elfoa.drone;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Pierre Colomb
 */
public class Container {

    private static final Integer SIZE = 100;

    private Set<Item> items = new HashSet<>();


    public void load (Item item){

        if(item == null){
            return;
        }

        if(isFull(item.getSize())){
            throw new IllegalArgumentException();
        }

        items.add(item);
    }

    public Integer getWeight(){
        return items.stream()
                    .mapToInt(Item::getWeight)
                    .sum();
    }

    private boolean isFull(int newItemWeigth){
        Integer sum = items.stream()
                           .mapToInt(Item::getSize)
                           .sum();

        return sum + newItemWeigth >= SIZE;


    }

}
