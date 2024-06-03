package com.proyecto.tutorlink.controller;
import com.proyecto.tutorlink.dto.TeacherDto;
import com.proyecto.tutorlink.entity.Image;
import com.proyecto.tutorlink.entity.Teacher;
import com.proyecto.tutorlink.exception.CustomException;
import com.proyecto.tutorlink.service.TeacherService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")

public class TeacherController {
    private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/admin/teachers")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        logger.info("Retrieved all teachers");
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/public/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable Long id) {
        try {
            Teacher teacher = teacherService.getTeacherById(id);
            logger.info("Retrieved teacher: {}", teacher);
            return ResponseEntity.ok(teacher);
        } catch (CustomException e) {
            logger.error("Error retrieving teacher:", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/public/index")
    public ResponseEntity<List<Teacher>> getRandomTeachers() {
        List<Teacher> randomTeachers = teacherService.getRandomTeachers();
        return ResponseEntity.ok(randomTeachers);
    }
    //obtener listado profesores con favoritos marcados (true) para el user entregado
    @GetMapping("/teachers/favorites/{userId}")
    public ResponseEntity<List<TeacherDto>> getTeachersWithFavorites(@PathVariable Long userId) {
        try {
            List<TeacherDto> teachers = teacherService.getAllTeachersWithFavorites(userId);
            if (teachers.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(teachers);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @GetMapping("/public/teachers/category")
    public ResponseEntity<List<Teacher>> getTeachersBySubjects(@RequestParam List<String> subjects) {
        List<Teacher> teachers = teacherService.getTeachersBySubjects(subjects);
        return ResponseEntity.ok(teachers);
    }
    @PostMapping("/admin/teachers")
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
    @DeleteMapping("/admin/teachers/{id}")
    public ResponseEntity<?> deleteTeacherById(@PathVariable Long id) {
        try {
            teacherService.deleteTeacherById(id);
            return ResponseEntity.ok().build();
        } catch (CustomException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/teachers/{id}")
    public ResponseEntity<?> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
        try {
            Teacher updatedTeacher = teacherService.updateTeacher(id, teacher);
            return ResponseEntity.ok(updatedTeacher);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    //filtro por caraacterísticas
    @GetMapping("/teachers/characteristics-filter")
    public ResponseEntity<List<Teacher>> getTeachersByCharacteristics(@RequestParam List<Long> characteristicIds) {
        try {
            List<Teacher> teachers = teacherService.getTeachersByCharacteristicIds(characteristicIds);
            return ResponseEntity.ok(teachers);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
