package com.proyecto.tutorlink.controller;

import com.proyecto.tutorlink.dto.TeacherDto;
import com.proyecto.tutorlink.entity.Teacher;
import com.proyecto.tutorlink.exception.CustomException;
import com.proyecto.tutorlink.repository.TeacherRepository;
import com.proyecto.tutorlink.service.TeacherService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TeacherController {

    private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeacherRepository teacherRepository;

    // Obtener todos los profesores (solo para administradores)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/teachers")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        logger.info("Retrieved all teachers");
        return ResponseEntity.ok(teachers);
    }

    // Obtener un profesor por ID (público)
    @GetMapping("/public/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable Long id) {
        try {
            Teacher teacher = teacherService.getTeacherById(id);
            logger.info("Retrieved teacher: {}", teacher);
            return ResponseEntity.ok(teacher);
        } catch (CustomException e) {
            logger.error("Error retrieving teacher: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Obtener profesores aleatorios (público)
    @GetMapping("/public/index")
    public ResponseEntity<List<Teacher>> getRandomTeachers() {
        List<Teacher> randomTeachers = teacherService.getRandomTeachers();
        return ResponseEntity.ok(randomTeachers);
    }

    // Obtener todos los profesores, con los favoritos del usuario marcados
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
//obtener SOLO los profesores favororitos de un user
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/favorites/user/{userId}")
    public ResponseEntity<List<Teacher>> getFavoriteTeachersByUser(@PathVariable Long userId) {
        List<Teacher> favoriteTeachers = teacherService.getFavoriteTeachersByUser(userId);
        return ResponseEntity.ok(favoriteTeachers);
    }

    // Obtener profesores por materias (público)
    @GetMapping("/public/teachers/category")
    public ResponseEntity<List<Teacher>> getTeachersBySubjects(@RequestParam List<String> subjects) {
        List<Teacher> teachers = teacherService.getTeachersBySubjects(subjects);
        return ResponseEntity.ok(teachers);
    }
    // Añadir un nuevo profesor (solo para administradores)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/teachers")
    public ResponseEntity<?> addTeacher(@RequestBody Teacher teacher) {
        try {
            Teacher savedTeacher = teacherService.addTeacher(teacher);
            logger.info("Added teacher: {}", savedTeacher);
            return ResponseEntity.ok(savedTeacher);
        } catch (IllegalStateException e) {
            logger.error("Error adding teacher: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Eliminar un profesor por ID (solo para administradores)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/teachers/{id}")
    public ResponseEntity<?> deleteTeacherById(@PathVariable Long id) {
        try {
            teacherService.deleteTeacherById(id);
            return ResponseEntity.ok().build();
        } catch (CustomException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Actualizar un profesor (solo para administradores)
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

    // Filtrar profesores por características
    @GetMapping("/teachers/characteristics-filter")
    public ResponseEntity<List<Teacher>> getTeachersByCharacteristics(@RequestParam List<Long> characteristicIds) {
        try {
            List<Teacher> teachers = teacherService.getTeachersByCharacteristicIds(characteristicIds);
            return ResponseEntity.ok(teachers);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Filtrar profesores por características y materias (pruebas)
    @GetMapping("/teachers/search")
    public ResponseEntity<List<Teacher>> getTeachersByFilter(@RequestParam List<String> subjects, @RequestParam List<Long> characteristicIds) {
        List<Teacher> teachers = teacherService.getTeachersByFilter(subjects, characteristicIds);
        return ResponseEntity.ok(teachers);
    }

    // Búsqueda de profesores por palabra clave
    @GetMapping("/teachers/keywordsearch")
    public ResponseEntity<List<Teacher>> searchTeachers(@RequestParam String keyword) {
        List<Teacher> teachers = teacherService.getTeachersByKeyword(keyword);
        Set<Teacher> uniqueTeachers = new HashSet<>(teachers);
        return ResponseEntity.ok(new ArrayList<>(uniqueTeachers));
    }

    // Búsqueda de profesores por disponibilidad y materia
    @GetMapping("/available")
    public List<Teacher> getAvailableTeachers(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam("subjectTitle") String subjectTitle) {
        return teacherService.getAvailableTeachers(startDate, endDate, subjectTitle);
    }
}
