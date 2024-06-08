package com.proyecto.tutorlink.repository;
import com.proyecto.tutorlink.entity.Subject;
import com.proyecto.tutorlink.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long>, JpaSpecificationExecutor<Teacher> {
    boolean existsByDni(String dni);
    List<Teacher> findBySubjectTitle(String subjectTitle);
    List<Teacher> findBySubjectTitleIn(List<String> subjectTitles);

    @Query("SELECT t FROM Teacher t JOIN t.characteristics c WHERE c.id IN :characteristicIds GROUP BY t HAVING COUNT(t) = :count")
    List<Teacher> findByCharacteristicIds(@Param("characteristicIds") List<Long> characteristicIds, @Param("count") long count);

    // PRUEBAS FILTRADO MIXTO por subjects y characteristics

    @Query("SELECT t FROM Teacher t JOIN t.subject s WHERE s.title IN :subjectTitles")
    List<Teacher> findBySubjectTitles(@Param("subjectTitles") List<String> subjectTitles);


    @Query("SELECT t FROM Teacher t JOIN t.favorites f WHERE f.user.id = :userId")
    List<Teacher> findFavoriteTeachersByUser(@Param("userId") Long userId);

    List<Teacher> findBySubject(Subject subject);
}
