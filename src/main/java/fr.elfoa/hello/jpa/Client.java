package fr.elfoa.hello.jpa;

import javax.persistence.*;
import java.util.List;

/**
 * @author CAMPREDON & CHOMONT
 */

@Entity
public class Client {

    @Id
    @GeneratedValue
    private Integer id;
    private String mail;
    private String nom;
    private String telephone;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID")
    private List<Adresse> adresse;

    public Client(){}

    public Client(Integer id, String mail, String nom, String telephone, List<Adresse> adresse) {
        this.id = id;
        this.mail = mail;
        this.nom = nom;
        this.telephone = telephone;
        this.adresse = adresse;
    }

    public Client(String mail, String nom, String telephone, List<Adresse> adresse) {
        this.mail = mail;
        this.nom = nom;
        this.telephone = telephone;
        this.adresse = adresse;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<Adresse> getAdresse() {
        return adresse;
    }

    public void setAdresse(List<Adresse> adresse) {
        this.adresse = adresse;
    }
}
