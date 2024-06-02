package com.proyecto.tutorlink.repository;
import com.proyecto.tutorlink.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserId(Long userId);
    List<Reservation> findByTeacherId(Long teacherId);


        @Query("SELECT r FROM Reservation r WHERE r.teacher.id = :teacherId AND NOT (r.endTime <= :startTime OR r.startTime >= :endTime)")
        List<Reservation> findReservationsByTeacherAndTimeRange(Long teacherId, LocalDateTime startTime, LocalDateTime endTime);
}



