package com.proyecto.tutorlink.repository;

import com.proyecto.tutorlink.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.teacher.id = :teacherId AND ((r.startTime < :endTime AND r.endTime > :startTime) OR (r.startTime < :endTime AND r.endTime > :startTime))")
    List<Reservation> findReservationsByTeacherAndTimeRange(
            @Param("teacherId") Long teacherId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );

    //get reservation by teacher
    @Query("SELECT r FROM Reservation r WHERE r.teacher.id = :teacherId")
    List<Reservation> findReservationsByTeacherId(@Param("teacherId") Long teacherId);

    //find revevation by user
    @Query("SELECT r FROM Reservation r WHERE r.user.id = :userId")
    List<Reservation> findReservationsByUserId(@Param("userId") Long userId);

}
