package com.proyecto.tutorlink.service;

import com.proyecto.tutorlink.entity.Availability;
import com.proyecto.tutorlink.repository.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;

@Service
public class AvailabilityService {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    public List<Availability> getAllAvailability() {
        return availabilityRepository.findAll();
    }
    public List<Availability> getAvailabilityByTeacher(Long teacherId) {
        return availabilityRepository.findByTeacherId(teacherId);
    }

    public List<Availability> getAvailabilityByTeacherAndDay(Long teacherId, DayOfWeek dayOfWeek) {
        return availabilityRepository.findByTeacherIdAndDayOfWeek(teacherId, dayOfWeek);
    }

    // Métodos para añadir, actualizar, o eliminar disponibilidades
}

