package com.proyecto.tutorlink.service;

import com.proyecto.tutorlink.dto.AvailabilityDto;
import com.proyecto.tutorlink.entity.Availability;
import com.proyecto.tutorlink.repository.AvailabilityRepository;
import com.proyecto.tutorlink.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AvailabilityService {

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

    public Availability createAvailability(AvailabilityDto availabilityDto) {
        Availability availability = new Availability();
        availability.setTeacher(teacherRepository.findById(availabilityDto.getTeacherId()).orElseThrow(() -> new RuntimeException("Teacher not found")));
        availability.setDate(availabilityDto.getDate());
        availability.setStartTime(availabilityDto.getStartTime());
        availability.setEndTime(availabilityDto.getEndTime());
        return availabilityRepository.save(availability);
    }

    public Availability updateAvailability(Long id, AvailabilityDto availabilityDto) {
        Availability availability = availabilityRepository.findById(id).orElseThrow(() -> new RuntimeException("Availability not found"));
        availability.setDate(availabilityDto.getDate());
        availability.setStartTime(availabilityDto.getStartTime());
        availability.setEndTime(availabilityDto.getEndTime());
        return availabilityRepository.save(availability);
    }

    public void deleteAvailability(Long id) {
        availabilityRepository.deleteById(id);
    }
}
