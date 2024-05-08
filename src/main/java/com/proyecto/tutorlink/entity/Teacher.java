package com.proyecto.tutorlink.entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 100)
    private String name;

    @Column(nullable = false, unique = true)
    @NotNull(message = "DNI cannot be null")
    private String dni;

    @Column(length = 500)
    private String description;
    @Column(nullable = false)

    public String getDni() {
        return dni;
    }

    public @NotNull(message = "Name cannot be null") @Size(min = 2, max = 100) String getName() {
        return name;
    }

    public Teacher(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Teacher() {
    }
}
