package com.proyecto.tutorlink.repository;
import com.proyecto.tutorlink.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    // Métodos
}

