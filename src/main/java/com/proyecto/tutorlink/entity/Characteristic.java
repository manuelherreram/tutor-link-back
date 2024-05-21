package com.proyecto.tutorlink.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "characteristics")
public class Characteristic {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String Caracteristica1;

    @Column(length = 100)
    private String Caracteristica2;

    @Column(length = 100)
    private String Caracteristica3;

    @Column(length = 100)
    private String Caracteristica4;

    @Column(length = 100)
    private String Caracteristica5;

    @Column(length = 100)
    private String Caracteristica6;


    @OneToOne(mappedBy = "characteristic")
    private Teacher teacher;
    public Characteristic() {
    }

    public Characteristic(String caracteristica1, String caracteristica2, String caracteristica3, String caracteristica4, String caracteristica5, String caracteristica6) {
        Caracteristica1 = caracteristica1;
        Caracteristica2 = caracteristica2;
        Caracteristica3 = caracteristica3;
        Caracteristica4 = caracteristica4;
        Caracteristica5 = caracteristica5;
        Caracteristica6 = caracteristica6;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaracteristica1() {
        return Caracteristica1;
    }

    public void setCaracteristica1(String caracteristica1) {
        Caracteristica1 = caracteristica1;
    }

    public String getCaracteristica2() {
        return Caracteristica2;
    }

    public void setCaracteristica2(String caracteristica2) {
        Caracteristica2 = caracteristica2;
    }

    public String getCaracteristica3() {
        return Caracteristica3;
    }

    public void setCaracteristica3(String caracteristica3) {
        Caracteristica3 = caracteristica3;
    }

    public String getCaracteristica4() {
        return Caracteristica4;
    }

    public void setCaracteristica4(String caracteristica4) {
        Caracteristica4 = caracteristica4;
    }

    public String getCaracteristica5() {
        return Caracteristica5;
    }

    public void setCaracteristica5(String caracteristica5) {
        Caracteristica5 = caracteristica5;
    }

    public String getCaracteristica6() {
        return Caracteristica6;
    }

    public void setCaracteristica6(String caracteristica6) {
        Caracteristica6 = caracteristica6;
    }
}
