package com.proyecto.tutorlink.repository;
import com.proyecto.tutorlink.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    boolean existsByDni(String dni);
    List<Teacher> findBySubjectTitleIn(List<String> subjectTitles);

    @Query("SELECT t FROM Teacher t JOIN t.characteristics c WHERE c.id IN :characteristicIds GROUP BY t HAVING COUNT(t) = :count")
    List<Teacher> findByCharacteristicIds(@Param("characteristicIds") List<Long> characteristicIds, @Param("count") long count);


}
