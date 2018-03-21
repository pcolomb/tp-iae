package fr.elfoa.hello.jpa;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Commande
{
    // ============================================
    // ATTRIBUTS

    @Id
    @GeneratedValue
    private Integer id;

    private Date date;

    @ManyToOne(cascade = CascadeType.ALL)
    private Client client;

    @ManyToOne(cascade = CascadeType.ALL)
    private Adresse adresse;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Item> items;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    // ============================================
    // CONSTRUCTORS

    public Commande()
    {
        this.items = new ArrayList<>();
    }

    public Commande(Client client, Adresse adresse, Date date)
    {
        this.client = client;
        this.adresse = adresse;
        this.date = date;

        this.items = new ArrayList<Item>();
    }

    // ============================================
    // GETTERS / SETTERS

    public Date getDate() {
        return date;
    }
    public void setDate(String label) { this.date = date; }

    public Client getClient() {
        return client;
    }
    public void setClient(Integer taille) { this.client = client; }

    public Adresse getAdresse() {
        return adresse;
    }
    public void setAdresse(Integer poids) {
        this.adresse = adresse;
    }

    public List<Item> getItems() { return items; }
    public void addItem(Item item) { items.add(item); }

    // ============================================
    // PUBLIC METHODS

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Id : ").append(getId()).append("\n");
        sb.append("Date : ").append(getDate()).append("\n");

        if(items.size() == 0)
        {
            sb.append("Pas d'item\n");
        }
        else
        {
            for(Item item : items)
            {
                sb.append("====>\n");
                sb.append(item.toString());
                sb.append("<====\n");
            }
        }
        return sb.toString();
    }
}