package fr.elfoa.drone;

/**
 * @author Pierre Colomb
 */
public class Item {

    private Integer size;

    private Integer weight;

    public Item(Integer size, Integer weight) {
        this.size = size;
        this.weight = weight;
    }

    public Integer getSize() {
        return size;
    }



    public Integer getWeight() {
        return weight;
    }
}
