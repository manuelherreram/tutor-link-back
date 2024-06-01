package com.proyecto.tutorlink.dto;

import com.proyecto.tutorlink.entity.Subject;

public class SubjectDto {
    private Long id;
    private String title;
    private String url;

    public SubjectDto(Subject subject) {
        this.id = subject.getId();
        this.title = subject.getTitle();
        this.url = subject.getUrl();
    }

    // Getters and Setters

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
