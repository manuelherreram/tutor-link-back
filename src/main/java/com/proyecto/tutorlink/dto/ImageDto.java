package com.proyecto.tutorlink.dto;

import com.proyecto.tutorlink.entity.Image;

public class ImageDto {
    private Long id;
    private String url;
    private String title;

    public ImageDto(Image image) {
        this.id = image.getId();
        this.url = image.getUrl();
        this.title = image.getTitle();
    }

    // Getters and Setters

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
