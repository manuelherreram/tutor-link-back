package com.proyecto.tutorlink.dto;

import com.proyecto.tutorlink.entity.Characteristic;

public class CharacteristicDto {
    private Long id;
    private String name;
    private String url;

    public CharacteristicDto(Characteristic characteristic) {
        this.id = characteristic.getId();
        this.name = characteristic.getName();
        this.url = characteristic.getUrl();
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
