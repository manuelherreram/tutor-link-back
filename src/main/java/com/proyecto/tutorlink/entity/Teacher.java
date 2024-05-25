package com.proyecto.tutorlink.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "teacher_characteristic",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "characteristic_id")
    )
    private List<Characteristic> characteristics = new ArrayList<>();


    public Teacher() {
    }

    public Teacher(Long id, String name, String dni, String description, Subject subject) {
        this.id = id;
        this.name = name;
        this.dni = dni;
        this.description = description;
        this.subject = subject;
    }

    public Teacher(String name, String dni, String description, Subject subject, List<Characteristic> characteristics) {
        this.name = name;
        this.dni = dni;
        this.description = description;
        this.subject = subject;
        this.characteristics = characteristics;
    }



    // Getters y setters
    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

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

    public String getDni() {
        return dni;
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<Characteristic> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<Characteristic> characteristics) {
        this.characteristics = characteristics;
    }

    public void addCharacteristic(Characteristic characteristic) {
        this.characteristics.add(characteristic);
    }
}
