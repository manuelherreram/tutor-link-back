package com.proyecto.tutorlink.dto;
public class RatingDto {
    private Double rating;
    private Long teacherId;
    private Long userId;
    private String comment;

    public RatingDto(Double rating, Long teacherId, Long userId, String comment) {
        this.rating = rating;
        this.teacherId = teacherId;
        this.userId = userId;
        this.comment = comment;
    }

    // getters y setters

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
