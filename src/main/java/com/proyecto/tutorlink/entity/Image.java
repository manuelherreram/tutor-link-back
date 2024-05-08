package com.proyecto.tutorlink.entity;
import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    public void setUrl(String imageUrl) {
    }

    public void setTeacher(Teacher teacher) {
    }

    // Getters and setters
}

