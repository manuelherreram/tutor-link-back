package com.proyecto.tutorlink.controller;
import com.proyecto.tutorlink.dto.AvailabilityDto;
import com.proyecto.tutorlink.entity.Availability;
import com.proyecto.tutorlink.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/availabilities")
public class AvailabilityController {

    @Autowired
    private AvailabilityService availabilityService;

    // Crear disponibilidad
    @PostMapping
    public ResponseEntity<Availability> createAvailability(@RequestBody AvailabilityDto availabilityDto) {
        try {
            Availability availability = availabilityService.createAvailability(availabilityDto);
            return new ResponseEntity<>(availability, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Actualizar disponibilidad
    @PutMapping("/{id}")
    public ResponseEntity<Availability> updateAvailability(@PathVariable Long id, @RequestBody AvailabilityDto availabilityDto) {
        try {
            Availability updatedAvailability = availabilityService.updateAvailability(id, availabilityDto);
            return ResponseEntity.ok(updatedAvailability);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar disponibilidad
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAvailability(@PathVariable Long id) {
        try {
            availabilityService.deleteAvailability(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener disponibilidad por ID de profesor
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<Availability>> getAvailabilitiesByTeacher(@PathVariable Long teacherId) {
        List<Availability> availabilities = availabilityService.getAvailabilityByTeacher(teacherId);
        if (!availabilities.isEmpty()) {
            return new ResponseEntity<>(availabilities, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //obtener todas las availabilities

    @GetMapping
    public ResponseEntity<List<Availability>> getAllAvailabilities() {
        List<Availability> availabilities = availabilityService.getAllAvailability();
        if (!availabilities.isEmpty()) {
            return new ResponseEntity<>(availabilities, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
