package com.proyecto.tutorlink.service;

import com.proyecto.tutorlink.DataInitializer;
import com.proyecto.tutorlink.dto.AvailabilityDto;
import com.proyecto.tutorlink.entity.Availability;
import com.proyecto.tutorlink.repository.AvailabilityRepository;
import com.proyecto.tutorlink.repository.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;



@Service
public class AvailabilityService {
    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public List<Availability> getAllAvailability() {
        return availabilityRepository.findAll();
    }

    public List<Availability> getAvailabilityByTeacher(Long teacherId) {
        return availabilityRepository.findByTeacherId(teacherId);
    }

    public List<Availability> getAvailabilityByTeacherAndDate(Long teacherId, LocalDate date) {
        return availabilityRepository.findByTeacherIdAndDate(teacherId, date);
    }
    @Transactional
    public Availability createAvailability(AvailabilityDto availabilityDto) {
        try {
            Availability availability = new Availability();
            availability.setTeacher(teacherRepository.findById(availabilityDto.getTeacherId()).orElseThrow(() -> new RuntimeException("Teacher not found")));
            availability.setDate(availabilityDto.getDate());
            availability.setStartTime(availabilityDto.getStartTime());
            availability.setEndTime(availabilityDto.getEndTime());
            return availabilityRepository.save(availability);
        } catch (Exception e) {

            log.error("Error creating availability: ", e);
            throw e; // O manejar según la política de errores de tu aplicación
        }
    }

@Transactional
    public Availability updateAvailability(Long id, AvailabilityDto availabilityDto) {
        Availability availability = availabilityRepository.findById(id).orElseThrow(() -> new RuntimeException("Availability not found"));
        availability.setDate(availabilityDto.getDate());
        availability.setStartTime(availabilityDto.getStartTime());
        availability.setEndTime(availabilityDto.getEndTime());
        return availabilityRepository.save(availability);
    }
@Transactional
    public void deleteAvailability(Long id) {
        availabilityRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllAvailabilities() {
        availabilityRepository.deleteAll();
    }
}
