package com.proyecto.tutorlink.repository;

import com.proyecto.tutorlink.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
        List<Rating> findByTeacherId(Long teacherId);

    List<Rating> findByUserId(Long userId);

    @Query("SELECT AVG(r.rating) FROM Rating r WHERE r.teacher.id = :teacherId")
    double findAverageRatingByTeacherId(Long teacherId);

    @Query("SELECT COUNT(r) FROM Rating r WHERE r.teacher.id = :teacherId")
    long countRatingsByTeacherId(Long teacherId);

}


