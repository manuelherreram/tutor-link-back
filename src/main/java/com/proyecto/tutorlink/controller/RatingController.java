package com.proyecto.tutorlink.controller;

import com.proyecto.tutorlink.dto.RatingDto;
import com.proyecto.tutorlink.dto.RatingResponseDto;
import com.proyecto.tutorlink.dto.RatingStatisticsDto;
import com.proyecto.tutorlink.entity.Rating;
import com.proyecto.tutorlink.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/teachers/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

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
    public ResponseEntity<?> getRatingsStatisticsForTeacher(@PathVariable Long teacherId) {
        try {
            RatingStatisticsDto stats = ratingService.getRatingsStatisticsForTeacher(teacherId);
            if (stats == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(stats);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body("Error retrieving ratings: " + ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error: " + e.getMessage());
        }
    }


    @PutMapping("/{ratingId}")
    public ResponseEntity<?> updateRating(@PathVariable Long ratingId, @RequestBody RatingDto ratingDto) {
        try {
            Rating updatedRating = ratingService.updateRating(ratingId, ratingDto);
            return ResponseEntity.ok(updatedRating);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body("Error updating rating: " + ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{ratingId}")
    public ResponseEntity<?> deleteRating(@PathVariable Long ratingId) {
        try {
            ratingService.deleteRating(ratingId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body("Error deleting rating: " + ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error: " + e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<String> getRatingsByUser(@PathVariable Long userId) {
        try {
            List<RatingResponseDto> ratings = ratingService.getRatingsByUser(userId);
            return ResponseEntity.ok(ratings.toString());
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body("Error retrieving ratings: " + ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error: " + e.getMessage());
        }
    }
}
