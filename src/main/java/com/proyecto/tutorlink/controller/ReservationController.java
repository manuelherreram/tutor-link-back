package com.proyecto.tutorlink.controller;

import com.proyecto.tutorlink.dto.ReservationDto;
import com.proyecto.tutorlink.entity.Reservation;
import com.proyecto.tutorlink.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/add")
    public ResponseEntity<?> createReservation(@RequestBody ReservationDto reservationDto) {
        try {
            Reservation reservation = reservationService.bookClass(reservationDto);
            return ResponseEntity.ok(reservation);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //eliminar reservation for id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long id) {
        try {
            reservationService.deleteReservation(id);
            return ResponseEntity.ok("Reservation deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
 //obtener todas las reservas
    @GetMapping("/all")
    public ResponseEntity<?> getAllReservations() {
        try {
            return ResponseEntity.ok(reservationService.getAllReservations());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



}
