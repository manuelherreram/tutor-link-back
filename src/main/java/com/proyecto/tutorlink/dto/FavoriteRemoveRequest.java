package com.proyecto.tutorlink.dto;

public class FavoriteRemoveRequest {
    private Long userId;
    private Long teacherId;

    // Constructor vacío necesario para deserialización
    public FavoriteRemoveRequest() {}

    // Getters y setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
}
