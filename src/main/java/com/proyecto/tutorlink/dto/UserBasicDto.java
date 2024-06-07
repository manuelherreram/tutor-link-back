package com.proyecto.tutorlink.dto;

import com.proyecto.tutorlink.entity.User;

public class UserBasicDto {
    private Long id;
    private String firstName;
    private String lastName;

    public UserBasicDto() {
    }

    public UserBasicDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }

    public UserBasicDto(Long id, String uid, String email, String s, String role) {


    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
