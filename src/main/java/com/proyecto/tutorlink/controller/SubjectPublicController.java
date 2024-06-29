package com.proyecto.tutorlink.controller;
import com.proyecto.tutorlink.entity.Subject;
import com.proyecto.tutorlink.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
    @RequestMapping("/api/public/subjects")

        public class SubjectPublicController {
        @Autowired
        private SubjectService subjectService;

        @GetMapping("/list")
        public ResponseEntity<List<Subject>> getAllSubjects() {
            try {
                List<Subject> subjects = subjectService.getAllSubjects();
                return ResponseEntity.ok(subjects);
            } catch (Exception e) {
                return ResponseEntity.internalServerError().body(null);
            }
        }
    }
