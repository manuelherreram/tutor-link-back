package com.proyecto.tutorlink.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url;
    @Column(length = 255)
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    @JsonIgnore
    private Teacher teacher;
    public Image() {
    }

    public Image(String url, String title, Teacher teacher) {
        this.url = url;
        this.title = title;
        this.teacher = teacher;
    }

    public Image(Long id, String url, String title, Teacher teacher) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.teacher = teacher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
