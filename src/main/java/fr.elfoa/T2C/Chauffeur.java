package fr.elfoa.T2C;

import javax.persistence.*;
import java.util.Objects;

/**
 * Chauffeur est annoté @Entity pour le mappage avec la BDD
 */

@Entity
public class Chauffeur {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer salaire;

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    private Bus bus;

    public Chauffeur() {}

    public Chauffeur(String name, Integer salaire) {
        this.name = name;
        this.salaire = salaire;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getSalaire() {
        return salaire;
    }

    public void setBus(Bus s) {
        this.bus = s;
    }

    public Bus getBus() {
        return bus;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Chauffeur)) {
            return false;
        } else {
            Chauffeur o = (Chauffeur) other;
            return (this.name.equals(o.name) && this.salaire.equals(o.salaire) && this.bus.equals(o.bus));
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, salaire);
    }
}
