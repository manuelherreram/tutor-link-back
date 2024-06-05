package com.proyecto.tutorlink.dto;

import com.proyecto.tutorlink.entity.Rating;

public class RatingResponseDto {
    private Long id;
    private Double rating;
    private String comment;
    private Long teacherId;
    private Long userId;

    public RatingResponseDto(Rating rating) {
        this.id = rating.getId();
        this.rating = rating.getRating();
        this.comment = rating.getComment();
        this.teacherId = rating.getTeacher().getId();
        this.userId = rating.getUser().getId();
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
