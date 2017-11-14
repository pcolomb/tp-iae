package fr.elfoa.hello.jpa;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author CAMPREDON & CHOMONT
 */

@Entity
public class Commande {

    @Id
    @GeneratedValue
    private int id;
    private java.util.Date date;
    @OneToOne
    private Client client;
    @OneToOne
    private Adresse adresse;
    @OneToMany
    private List<Item> items;

    public Commande() {
    }

    public Commande(Date date, Client client, Adresse adresse) {
        this.date = date;
        this.client = client;
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
}
