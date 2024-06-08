package com.proyecto.tutorlink.dto;

import java.time.LocalDateTime;

public class ReservationDto {
    private Long id;
    private UserBasicDto user;
    private TeacherBasicDto teacher;
    private Long userId; // Direct access to userId
    private Long teacherId; // Direct access to teacherId
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;

    // Default constructor
    public ReservationDto() {
    }

    // Full constructor
    public ReservationDto(Long id, UserBasicDto user, TeacherBasicDto teacher, LocalDateTime startTime, LocalDateTime endTime, String status) {
        this.id = id;
        this.user = user;
        this.teacher = teacher;
        this.userId = user != null ? user.getId() : null;
        this.teacherId = teacher != null ? teacher.getId() : null;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserBasicDto getUser() {
        return user;
    }

    public void setUser(UserBasicDto user) {
        this.user = user;
        this.userId = user != null ? user.getId() : null; // Ensure synchronization
    }

    public TeacherBasicDto getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherBasicDto teacher) {
        this.teacher = teacher;
        this.teacherId = teacher != null ? teacher.getId() : null; // Ensure synchronization
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
