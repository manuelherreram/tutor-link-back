package com.proyecto.tutorlink.controller;

import com.proyecto.tutorlink.dto.SubjectDto;
import com.proyecto.tutorlink.dto.SubjectInputDto;
import com.proyecto.tutorlink.entity.Subject;
import com.proyecto.tutorlink.exception.CustomException;
import com.proyecto.tutorlink.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/subjects")
public class SubjectAdminController {

    @Autowired
    private SubjectService subjectService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addSubject(@RequestBody SubjectInputDto subjectInputDto) {
        try {
            SubjectDto newSubject = subjectService.addSubject(subjectInputDto);
            return ResponseEntity.ok(newSubject);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred while adding the subject");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable Long id) throws CustomException {
        subjectService.deleteSubject(id);
        return new ResponseEntity<>("Subject eliminado correctamente", HttpStatus.OK);
    }
}
