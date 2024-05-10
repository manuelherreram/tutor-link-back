package com.proyecto.tutorlink.entity;

import javax.persistence.*;

@Entity
@Table(name = "SUBJECTS")
public class Subject {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String title;

    @OneToOne(mappedBy = "subject")
    private Teacher teacher;

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
