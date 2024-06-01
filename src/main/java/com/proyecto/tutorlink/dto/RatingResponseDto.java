package com.proyecto.tutorlink.dto;

import com.proyecto.tutorlink.entity.Rating;
import com.proyecto.tutorlink.entity.Teacher;
import com.proyecto.tutorlink.entity.User;

public class RatingResponseDto {
    private Long id;
    private Double rating;
    private String comment;
    private TeacherBasicDto teacher;
    private UserBasicDto user;

    public RatingResponseDto(Rating rating) {
        this.id = rating.getId();
        this.rating = rating.getRating();
        this.comment = rating.getComment();
        this.teacher = new TeacherBasicDto(rating.getTeacher());
        this.user = new UserBasicDto(rating.getUser());
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Double getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public TeacherBasicDto getTeacher() {
        return teacher;
    }

    public UserBasicDto getUser() {
        return user;
    }
}


