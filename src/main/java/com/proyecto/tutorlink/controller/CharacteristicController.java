package com.proyecto.tutorlink.controller;

import com.proyecto.tutorlink.entity.Characteristic;

import com.proyecto.tutorlink.service.CharacteristicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/admin/characteristic")
    public class CharacteristicController {
        private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);

        @Autowired
        private CharacteristicService characteristicService;

        @PreAuthorize("hasRole('ADMIN')")
        @PostMapping("/add")
        public ResponseEntity<?> addCharacteristic(@RequestBody Characteristic characteristic) {
            try {
                Characteristic newCharacteristic = characteristicService.addCharacteristic(characteristic);
                return ResponseEntity.ok(newCharacteristic);
            } catch (IllegalStateException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            } catch (Exception e) {
                return ResponseEntity.internalServerError().body("An error occurred while adding the characteristic");
            }
        }

        /*@GetMapping("/admin/characteristic")
        public ResponseEntity<List<Characteristic>> getAllTeachers() {
            List<Characteristic> characteristics = characteristicService.getAllCharacteristic();
            logger.info("Retrieved all characteristics");
            return ResponseEntity.ok(characteristics);
        }*/
    }
