package com.proyecto.tutorlink.dto;

import com.proyecto.tutorlink.entity.User;

public class UserBasicDto {
    private Long id;
    private String firstName;
    private String lastName;

    public UserBasicDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }

    public UserBasicDto() {
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
