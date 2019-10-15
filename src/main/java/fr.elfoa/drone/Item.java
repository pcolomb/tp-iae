package fr.elfoa.drone;

import java.util.Objects;

/**
 * @author Pierre Colomb
 */
public class Item {

    private String label;

    private Integer size;

    private Integer weight;

    public Item(){

    }

    public Item(String label, Integer size, Integer weight){
        this.label = label;
        this.size = size;
        this.weight = weight;
    }



    public Integer getSize() {
        return size;
    }


    public void setSize(Integer size) {
        this.size = size;
    }


    public Integer getWeight() {
        return weight;
    }



    public void setWeight(Integer weight) {
        this.weight = weight;
    }



    public String getLabel() {
        return label;
    }



    public void setLabel(String label) {
        this.label = label;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Item))
            return false;
        Item item = (Item) o;
        return Objects.equals(label, item.label) &&
               Objects.equals(size, item.size) &&
               Objects.equals(weight, item.weight);
    }



    @Override
    public int hashCode() {
        return Objects.hash(label, size, weight);
    }
}
