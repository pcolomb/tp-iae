package fr.elfoa.T2C;

import javax.persistence.*;
import java.util.Objects;

/**
 * Bus est annoté @Entity pour le mappage avec la BDD
 */

@Entity
public class Bus {
    @Id
    @GeneratedValue
    private Integer id;

    private String plaque;

    public Bus() {}

    public Bus(String plaque) {
        this.plaque = plaque;
    }

    public Integer getId() {
        return id;
    }

    public String getPlaque() {
        return plaque;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Bus)) {
            return false;
        } else {
            Bus o = (Bus)other;
            return this.plaque.equals(o.plaque);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, plaque);
    }
}