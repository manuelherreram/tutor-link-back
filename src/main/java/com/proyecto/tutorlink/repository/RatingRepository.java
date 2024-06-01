package com.proyecto.tutorlink.repository;

import com.proyecto.tutorlink.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
        List<Rating> findByTeacherId(Long teacherId);
    }


