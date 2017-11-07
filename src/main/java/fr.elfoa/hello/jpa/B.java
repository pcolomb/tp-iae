package fr.elfoa.hello.jpa;

import javax.persistence.*;

/**
 * @author Pierre Colomb
 */
@Entity
public class B {

    @Id
    @GeneratedValue
    private Integer id;

    private String b_1;

    private String b_2;

    @OneToOne(cascade = CascadeType.ALL)
    private C c;

    public B(){
    }

    public B(String b_1, String b_2,C c) {
        this.b_1 = b_1;
        this.b_2 = b_2;
        this.c = c;
    }



    public Integer getId() {
        return id;
    }



    public void setId(Integer id) {
        this.id = id;
    }



    public String getB_1() {
        return b_1;
    }



    public void setB_1(String b_1) {
        this.b_1 = b_1;
    }



    public String getB_2() {
        return b_2;
    }



    public void setB_2(String b_2) {
        this.b_2 = b_2;
    }



    public C getC() {
        return c;
    }



    public void setC(C c) {
        this.c = c;
    }
}
