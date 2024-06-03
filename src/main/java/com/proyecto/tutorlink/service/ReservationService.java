package com.proyecto.tutorlink.service;

import com.proyecto.tutorlink.dto.ReservationDto;
import com.proyecto.tutorlink.dto.TeacherBasicDto;
import com.proyecto.tutorlink.dto.UserBasicDto;
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

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        Teacher teacher = teacherRepository.findById(reservationDto.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found with ID: " + reservationDto.getTeacherId()));
        User user = userRepository.findById(reservationDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + reservationDto.getUserId()));

        LocalDateTime startTime = reservationDto.getStartTime();
        LocalDateTime endTime = reservationDto.getEndTime();

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
        DayOfWeek dayOfWeek = startTime.getDayOfWeek();
        List<Availability> availabilities = availabilityRepository.findByTeacherIdAndDayOfWeek(teacher.getId(), dayOfWeek);
        return availabilities.stream().anyMatch(availability ->
                !startTime.toLocalTime().isBefore(availability.getStartTime()) &&
                        !endTime.toLocalTime().isAfter(availability.getEndTime())
        );
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    public List<ReservationDto> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ReservationDto convertToDto(Reservation reservation) {
        ReservationDto dto = new ReservationDto();
        dto.setId(reservation.getId());
        dto.setUser(convertUserToDto(reservation.getUser()));
        dto.setTeacher(convertTeacherToDto(reservation.getTeacher()));
        dto.setStartTime(reservation.getStartTime());
        dto.setEndTime(reservation.getEndTime());
        dto.setStatus(reservation.getStatus().name());
        return dto;
    }

    private UserBasicDto convertUserToDto(User user) {
        UserBasicDto dto = new UserBasicDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        return dto;
    }

    private TeacherBasicDto convertTeacherToDto(Teacher teacher) {
        TeacherBasicDto dto = new TeacherBasicDto();
        dto.setId(teacher.getId());
        dto.setName(teacher.getName());
        dto.setDni(teacher.getDni());
        dto.setSubjectTitle(teacher.getSubject() != null ? teacher.getSubject().getTitle() : null);
        return dto;
    }
}
