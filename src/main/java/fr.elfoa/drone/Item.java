package fr.elfoa.drone;

/**
 * @author Pierre Colomb
 */
public class Item {

    private Integer size = new Integer(0);

    private Integer weight = new Integer(0);

    public Item() {}

    public Item(int size, int weight) {
        this.size = new Integer(size);
        this.weight = new Integer(weight);
    }

    public Integer getSize() {
        return size;
    }



    public Integer getWeight() {
        return weight;
    }
}
