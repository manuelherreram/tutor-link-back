package com.proyecto.tutorlink.repository;
import com.proyecto.tutorlink.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Optional<Subject> findByTitle(String title);

    boolean existsByTitle(String subject);
}