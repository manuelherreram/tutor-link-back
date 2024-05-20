package com.proyecto.tutorlink.controller;
import com.proyecto.tutorlink.entity.Subject;
import com.proyecto.tutorlink.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addSubject(@RequestBody Subject subject) {
        try {
            Subject newSubject = subjectService.addSubject(subject);
            return ResponseEntity.ok(newSubject);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred while adding the subject");
        }
    }
}
