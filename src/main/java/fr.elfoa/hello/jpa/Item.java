package fr.elfoa.hello.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author CAMPREDON & CHOMONT
 */

@Entity
public class Item {

    @Id
    @GeneratedValue
    private int id;
    private String label;
    private int size;
    private int weight;

    public Item(String label, int size, int weight) {
        this.label = label;
        this.size = size;
        this.weight = weight;
    }

    public Item() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
