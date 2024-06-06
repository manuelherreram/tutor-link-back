package com.proyecto.tutorlink.controller;

import com.proyecto.tutorlink.entity.Characteristic;

import com.proyecto.tutorlink.entity.Teacher;
import com.proyecto.tutorlink.exception.CustomException;
import com.proyecto.tutorlink.service.CharacteristicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api")
    public class CharacteristicController {
        private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);

        @Autowired
        private CharacteristicService characteristicService;

        @PreAuthorize("hasRole('ADMIN')")
        @PostMapping("/admin/characteristics/add")
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

        @GetMapping("/public/characteristics/list")
        public ResponseEntity<List<Characteristic>> getAllCharacteristics() {
            List<Characteristic> characteristics = characteristicService.getAllCharacteristics();
            logger.info("Retrieved all characteristics");
            return ResponseEntity.ok(characteristics);
        }

        @GetMapping("/public/characteristics/{id}")
        public ResponseEntity<?> getCharacteristicById(@PathVariable Long id) {
            try {
                Characteristic characteristic = characteristicService.getCharacteristicById(id);
                logger.info("Retrieved characteristic: {}", characteristic);
                return ResponseEntity.ok(characteristic);
            } catch (CustomException e) {
                logger.error("Error retrieving characteristic:", e.getMessage());
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        @PreAuthorize("hasRole('ADMIN')")
        @PutMapping("/admin/characteristics/actualizar")
        public Characteristic actualizarCharacteristic(@RequestBody Characteristic characteristic){
            return characteristicService.actualizarCharacteristic(characteristic);
        }
        @PreAuthorize("hasRole('ADMIN')")
        @DeleteMapping("admin/characteristics/eliminar/{id}")
        public ResponseEntity<?> deleteCharacteristic(@PathVariable Long id) throws CustomException {
            characteristicService.deleteCharacteristic(id);
            return new ResponseEntity<>("Characteristic eliminado correctamente", HttpStatus.OK);
        }
    }
