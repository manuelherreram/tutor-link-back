package com.proyecto.tutorlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.tutorlink.entity.Availability;

import java.time.DayOfWeek;
import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    List<Availability> findByTeacherId(Long teacherId);
    List<Availability> findByTeacherIdAndDayOfWeek(Long teacherId, DayOfWeek dayOfWeek);
}
