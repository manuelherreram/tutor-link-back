package com.proyecto.tutorlink.repository;
import com.proyecto.tutorlink.entity.Subject;
import com.proyecto.tutorlink.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    boolean existsByDni(String dni);
    List<Teacher> findBySubjectTitleIn(List<String> subjectTitles);


    List<Teacher> findBySubject(Subject subject);
}
