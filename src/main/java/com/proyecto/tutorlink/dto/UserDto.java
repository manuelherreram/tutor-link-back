package com.proyecto.tutorlink.dto;

public class UserDto {
    private final Long Id;
    private String uid;
    private String email;
    private String displayName;
    private String role;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String city;
    private String country;

    // Constructor que incluye todos los campos
    public UserDto(Long Id,String uid, String email, String displayName, String role,
                   String firstName, String lastName, String phone, String address,
                   String city, String country) {
        this.Id = Id;
        this.uid = uid;
        this.email = email;
        this.displayName = displayName;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.country = country;
    }

    public UserDto(Long id, String uid, String email, String displayName, String role) {
        this.Id = id;
        this.uid = uid;
        this.email = email;
        this.displayName = displayName;
        this.role = role;
    }

    // Getters y setters
    public Long getId() { return Id; }
    public String getUid() { return uid; }
    public void setUid(String uid) { this.uid = uid; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}