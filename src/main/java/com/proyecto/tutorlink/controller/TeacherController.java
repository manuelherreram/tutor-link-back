package com.proyecto.tutorlink.controller;
import com.proyecto.tutorlink.entity.Teacher;
import com.proyecto.tutorlink.exception.CustomException;
import com.proyecto.tutorlink.service.TeacherService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private TeacherService teacherService;
    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        logger.info("Retrieved all teachers");
        return ResponseEntity.ok(teachers);
    }
    @PostMapping
    public ResponseEntity<?> addTeacher(@RequestBody Teacher teacher) {
        try {
            Teacher savedTeacher = teacherService.addTeacher(teacher);
            logger.info("Added teacher: {}", savedTeacher);
            return ResponseEntity.ok(savedTeacher);
        } catch (IllegalStateException e) {
            logger.error("Error adding teacher:", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
