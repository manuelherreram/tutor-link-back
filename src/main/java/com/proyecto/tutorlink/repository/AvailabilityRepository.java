package com.proyecto.tutorlink.repository;
import com.proyecto.tutorlink.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {

    List<Availability> findByTeacherIdAndDateBetween(Long teacherId, LocalDate startDate, LocalDate endDate);

    List<Availability> findByTeacherId(Long teacherId);
    //List<Availability> findByDateBetweenAndTeacherSubjectTitle(LocalDate startDate, LocalDate endDate, String subject);
    List<Availability> findByTeacherIdAndDate(Long teacherId, LocalDate date);


}

