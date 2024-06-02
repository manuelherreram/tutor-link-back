package com.proyecto.tutorlink.dto;

import java.time.LocalDateTime;

public class ReservationDto {
    private Long userId;
    private Long teacherId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public ReservationDto() {
    }

    public ReservationDto(Long userId, Long teacherId, LocalDateTime startTime, LocalDateTime endTime) {
        this.userId = userId;
        this.teacherId = teacherId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}

