package fr.elfoa.tp2;

import javax.persistence.*;
import java.util.Objects;

/**
 * Objet annoté @Entity afin d'être mappé avec la BDD (JPA)
 */

@Entity
public class Student {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer mark;

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    private School school;

    public Student() {}

    public Student(String name, Integer mark) {
        this.name = name;
        this.mark = mark;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getMark() {
        return mark;
    }

    public void setSchool(School s) {
        this.school = s;
    }

    public School getSchool() {
        return school;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Student)) {
            return false;
        } else {
            Student o = (Student)other;
            return (this.name.equals(o.name) && this.mark.equals(o.mark) && this.school.equals(o.school));
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, mark);
    }
}
