package com.proyecto.tutorlink.dto;

import com.proyecto.tutorlink.entity.Teacher;
import java.util.List;
import java.util.stream.Collectors;

public class TeacherDto {
    private Long id;
    private String name;
    private String dni;
    private String description;
    private SubjectDto subject;
    private List<ImageDto> images;
    private List<CharacteristicDto> characteristics;
    private boolean isFavorite;

    public TeacherDto(Teacher teacher, boolean isFavorite) {
        this.id = teacher.getId();
        this.name = teacher.getName();
        this.dni = teacher.getDni();
        this.description = teacher.getDescription();
        this.subject = teacher.getSubject() != null ? new SubjectDto(teacher.getSubject()) : null;
        this.images = teacher.getImages().stream().map(ImageDto::new).collect(Collectors.toList());
        this.characteristics = teacher.getCharacteristics().stream().map(CharacteristicDto::new).collect(Collectors.toList());
        this.isFavorite = isFavorite;
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

    public SubjectDto getSubject() {
        return subject;
    }

    public void setSubject(SubjectDto subject) {
        this.subject = subject;
    }

    public List<ImageDto> getImages() {
        return images;
    }

    public void setImages(List<ImageDto> images) {
        this.images = images;
    }

    public List<CharacteristicDto> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<CharacteristicDto> characteristics) {
        this.characteristics = characteristics;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
