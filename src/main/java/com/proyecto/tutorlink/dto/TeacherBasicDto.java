package com.proyecto.tutorlink.dto;

import com.proyecto.tutorlink.entity.Teacher;

public class TeacherBasicDto {
    private Long id;
    private String name;
    private String subjectTitle;

    public TeacherBasicDto(Teacher teacher) {
        this.id = teacher.getId();
        this.name = teacher.getName();
        this.subjectTitle = teacher.getSubject() != null ? teacher.getSubject().getTitle() : null;
    }

    public TeacherBasicDto() {
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }
}


