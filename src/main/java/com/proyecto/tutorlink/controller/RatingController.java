package com.proyecto.tutorlink.controller;

import com.proyecto.tutorlink.dto.RatingDto;
import com.proyecto.tutorlink.dto.RatingResponseDto;
import com.proyecto.tutorlink.entity.Rating;
import com.proyecto.tutorlink.repository.RatingRepository;
import com.proyecto.tutorlink.repository.TeacherRepository;
import com.proyecto.tutorlink.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/teachers/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private RatingRepository ratingRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addRating(@RequestBody RatingDto ratingDto) {
        try {
            Rating savedRating = ratingService.addRating(ratingDto);
            return ResponseEntity.ok(savedRating);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body("Error saving rating: " + ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error: " + e.getMessage());
        }
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<List<RatingResponseDto>> getRatingsForTeacher(@PathVariable Long teacherId) {
        List<RatingResponseDto> ratings = ratingService.getRatingsForTeacher(teacherId);
        return ResponseEntity.ok(ratings);
    }


}
