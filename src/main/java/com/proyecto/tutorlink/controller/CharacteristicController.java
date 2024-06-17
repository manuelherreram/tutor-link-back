package com.proyecto.tutorlink.controller;

import com.proyecto.tutorlink.dto.CharacteristicDto;
import com.proyecto.tutorlink.dto.CharacteristicInputDto;
import com.proyecto.tutorlink.entity.Characteristic;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CharacteristicController {
    private static final Logger logger = LoggerFactory.getLogger(CharacteristicController.class);

    @Autowired
    private CharacteristicService characteristicService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/characteristics/add")
    public ResponseEntity<?> addCharacteristic(@RequestBody CharacteristicInputDto characteristicInputDto) {
        try {
            CharacteristicDto newCharacteristic = characteristicService.addCharacteristic(characteristicInputDto);
            return ResponseEntity.ok(newCharacteristic);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred while adding the characteristic");
        }
    }

    @GetMapping("/public/characteristics/list")
    public ResponseEntity<List<CharacteristicDto>> getAllCharacteristics() {
        List<Characteristic> characteristics = characteristicService.getAllCharacteristics();
        List<CharacteristicDto> characteristicDtos = characteristics.stream().map(CharacteristicDto::new).collect(Collectors.toList());
        logger.info("Retrieved all characteristics");
        return ResponseEntity.ok(characteristicDtos);
    }

    @GetMapping("/public/characteristics/{id}")
    public ResponseEntity<?> getCharacteristicById(@PathVariable Long id) {
        try {
            Characteristic characteristic = characteristicService.getCharacteristicById(id);
            CharacteristicDto characteristicDto = new CharacteristicDto(characteristic);
            logger.info("Retrieved characteristic: {}", characteristicDto);
            return ResponseEntity.ok(characteristicDto);
        } catch (CustomException e) {
            logger.error("Error retrieving characteristic:", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/characteristics/update/{id}")
    public ResponseEntity<?> updateCharacteristic(@PathVariable Long id, @RequestBody CharacteristicInputDto characteristicInputDto) {
        try {
            Characteristic updatedCharacteristic = characteristicService.updateCharacteristic(id, characteristicInputDto);
            return ResponseEntity.ok(updatedCharacteristic);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred while updating the characteristic");
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/characteristics/eliminar/{id}")
    public ResponseEntity<?> deleteCharacteristic(@PathVariable Long id) throws CustomException {
        characteristicService.deleteCharacteristic(id);
        return new ResponseEntity<>("Characteristic eliminado correctamente", HttpStatus.OK);
    }
}
