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
    private Integer id;
    private java.util.Date date;
    @OneToOne
    private Client client;
    @OneToOne
    private Adresse adresse;
    @JoinColumn(name = "ID")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> items;

    public Commande() {
    }

    public Commande(Date date, Client client, Adresse adresse, List<Item> items) {
        this.date = date;
        this.client = client;
        this.adresse = adresse;
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
