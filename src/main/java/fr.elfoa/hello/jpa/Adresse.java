package fr.elfoa.hello.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author CAMPREDON & CHOMONT
 */

@Entity
public class Adresse {

    @Id
    @GeneratedValue
    private Integer id;

    private String cp;

    private int num;

    private String pays;

    private String voie;

    private String voieType;

    //CONSTRUCTOR
    public Adresse() {
    }

    public Adresse(String cp, int num, String pays, String voie, String voieType) {
        this.cp = cp;
        this.num = num;
        this.pays = pays;
        this.voie = voie;
        this.voieType = voieType;
    }

    //GETTERS SETTERS
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVoie() {
        return voie;
    }

    public void setVoie(String voie) {
        this.voie = voie;
    }

    public String getVoieType() {
        return voieType;
    }

    public void setVoieType(String voieType) {
        this.voieType = voieType;
    }
}
