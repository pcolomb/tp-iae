package fr.elfoa.hello.jpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client
{
    // ============================================
    // ATTRIBUTS

    @Id
    @GeneratedValue
    private Integer id;

    private String nom;
    private String prenom;
    private String mail;
    private String telephone;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Adresse> adresses;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Commande> commandes;

    // ============================================
    // CONSTRUCTORS

    public Client()
    {
        adresses = new ArrayList<Adresse>();
        commandes = new ArrayList<Commande>();
    }

    public Client(String nom, String prenom, String mail, String telephone)
    {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.telephone = telephone;
        adresses = new ArrayList<Adresse>();

        commandes = new ArrayList<>();
    }

    // ============================================
    // GETTERS / SETTERS

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<Adresse> getAdresses(){ return adresses; }
    public void addAdresse(Adresse adresse) { adresses.add(adresse); }

    public List<Commande> getCommandes(){ return commandes; }
    public void addCommande(Commande commande) { commandes.add(commande); }

    // ============================================
    // PUBLIC METHODS

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Id : ").append(getId()).append("\n");
        sb.append("Nom : ").append(getNom()).append("\n");
        sb.append("Prenom : ").append(getPrenom()).append("\n");
        sb.append("Mail : ").append(getMail()).append("\n");
        sb.append("Telephone : ").append(getTelephone()).append("\n");

        if(adresses.size() == 0)
        {
            sb.append("Pas d'adresse\n");
        }
        else
        {
            sb.append("Adresses :\n");
            for(Adresse adresse : adresses)
            {
                sb.append("====>\n");
                sb.append(adresse.toString());
                sb.append("<====\n");
            }

        }

        if(commandes.size() == 0)
        {
            sb.append("Pas de commande\n");
        }
        else
        {
            sb.append("Commandes :\n");
            for(Commande commande : commandes)
            {
                sb.append("====>\n");
                sb.append(commande.toString());
                sb.append("<====\n");
            }
        }
        return sb.toString();
    }
}
