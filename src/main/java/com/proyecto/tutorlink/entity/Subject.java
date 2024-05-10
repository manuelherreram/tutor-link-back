package com.proyecto.tutorlink.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SUBJECTS")
public class Subject {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String title;

    @OneToMany(mappedBy = "subject") // Relación con Teacher
    private List<Teacher> teachers; // Campo que mapea la relación con Teacher

    public Subject() {
    }

    public Subject(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
