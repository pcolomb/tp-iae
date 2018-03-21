package fr.elfoa.tp2;

import javax.persistence.*;
import java.util.Objects;

/**
 * Objet annoté @Entity afin d'être mappé avec la BDD (JPA)
 */

@Entity
public class School {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    public School() {}

    public School(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof School)) {
            return false;
        } else {
            School o = (School)other;
            return this.name.equals(o.name);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}