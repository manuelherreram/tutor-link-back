package com.proyecto.tutorlink.service;
import com.proyecto.tutorlink.dto.ReservationDto;
import com.proyecto.tutorlink.entity.Availability;
import com.proyecto.tutorlink.entity.Reservation;
import com.proyecto.tutorlink.entity.Teacher;
import com.proyecto.tutorlink.entity.User;
import com.proyecto.tutorlink.enums.ReservationStatus;
import com.proyecto.tutorlink.repository.ReservationRepository;
import com.proyecto.tutorlink.repository.TeacherRepository;
import com.proyecto.tutorlink.repository.UserRepository;
import com.proyecto.tutorlink.repository.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

    @Service
    public class ReservationService {

        @Autowired
        private ReservationRepository reservationRepository;
        @Autowired
        private TeacherRepository teacherRepository;
        @Autowired
        private UserRepository userRepository;
        @Autowired
        private AvailabilityRepository availabilityRepository;

        public Reservation bookClass(ReservationDto reservationDto) {
            Teacher teacher = teacherRepository.findById(reservationDto.getTeacherId()).orElseThrow(() -> new RuntimeException("Teacher not found"));
            User user = userRepository.findById(reservationDto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

            LocalDateTime startTime = reservationDto.getStartTime();
            LocalDateTime endTime = reservationDto.getEndTime();

            // Verificar disponibilidad general y de horario específico
            if (!isTeacherAvailable(teacher, startTime, endTime) || !hasNoScheduleConflicts(teacher, startTime, endTime)) {
                throw new RuntimeException("Teacher not available or schedule conflict exists");
            }

            Reservation reservation = new Reservation();
            reservation.setTeacher(teacher);
            reservation.setUser(user);
            reservation.setStartTime(startTime);
            reservation.setEndTime(endTime);
            reservation.setStatus(ReservationStatus.PENDING);
            return reservationRepository.save(reservation);
        }

        private boolean hasNoScheduleConflicts(Teacher teacher, LocalDateTime startTime, LocalDateTime endTime) {
            List<Reservation> overlappingReservations = reservationRepository.findReservationsByTeacherAndTimeRange(teacher.getId(), startTime, endTime);
            return overlappingReservations.isEmpty();
        }
    private boolean isTeacherAvailable(Teacher teacher, LocalDateTime startTime, LocalDateTime endTime) {
        List<Availability> availabilities = availabilityRepository.findByTeacherIdAndDayOfWeek(teacher.getId(), startTime.getDayOfWeek());
        for (Availability availability : availabilities) {
            if (startTime.toLocalTime().isAfter(availability.getStartTime()) && endTime.toLocalTime().isBefore(availability.getEndTime())) {
                return true;
            }
        }
        return false;
    }

//METODOS ACTUALIZAR CANCELAR RESERVAS
}
