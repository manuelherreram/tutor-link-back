package com.proyecto.tutorlink.dto;

import com.proyecto.tutorlink.entity.Teacher;

public class TeacherBasicDto {
    private Long id;
    private String name;
    private String dni;
    private String subjectTitle;

    public TeacherBasicDto() {
    }

    public TeacherBasicDto(Teacher teacher) {
        this.id = teacher.getId();
        this.name = teacher.getName();
        this.dni = teacher.getDni();
        this.subjectTitle = teacher.getSubject() != null ? teacher.getSubject().getTitle() : null;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }
}



