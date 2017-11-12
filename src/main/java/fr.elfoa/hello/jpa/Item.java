package fr.elfoa.hello.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Item
{
    // ============================================
    // ATTRIBUTS

    @Id
    @GeneratedValue
    private Integer id;

    private String label;
    private Integer taille;
    private Integer poids;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    // ============================================
    // CONSTRUCTORS

    public Item()
    {

    }

    public Item(String label, Integer taille, Integer poids)
    {
        this.label = label;
        this.taille = taille;
        this.poids = poids;
    }

    // ============================================
    // GETTERS / SETTERS

    public String getLabel() {
        return label;
    }
    public void setLabel(String label) { this.label = label; }

    public Integer getTaille() {
        return taille;
    }
    public void setTaille(Integer taille) { this.taille = taille; }

    public Integer getPoids() {
        return poids;
    }
    public void setPoids(Integer poids) {
        this.poids = poids;
    }

    // ============================================
    // PUBLIC METHODS

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Id : ").append(getId()).append("\n");
        sb.append("Label : ").append(getLabel()).append("\n");
        sb.append("Taille : ").append(getTaille()).append("\n");
        sb.append("Poids : ").append(getPoids()).append("\n");
        return sb.toString();
    }
}