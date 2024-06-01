package com.proyecto.tutorlink.controller;

import com.proyecto.tutorlink.entity.Favorite;
import com.proyecto.tutorlink.entity.Teacher;
import com.proyecto.tutorlink.entity.User;
import com.proyecto.tutorlink.exception.CustomException;
import com.proyecto.tutorlink.service.FavoriteService;
import com.proyecto.tutorlink.service.TeacherService;
import com.proyecto.tutorlink.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private UserService userService;

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/add")
    public ResponseEntity<?> addFavorite(@RequestBody FavoriteRequest favoriteRequest) {
        try {
            User user = userService.getUserById(favoriteRequest.getUserId());
            Teacher teacher = teacherService.getTeacherById(favoriteRequest.getTeacherId());
            if (user != null && teacher != null) {
                Favorite favorite = favoriteService.addFavorite(user, teacher);
                return ResponseEntity.ok(favorite);
            } else {
                throw new CustomException("Invalid user or teacher ID");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/remove/{favoriteId}")
    public ResponseEntity<?> removeFavorite(@PathVariable Long favoriteId) {
        try {
            favoriteService.removeFavorite(favoriteId);
            return ResponseEntity.ok("Favorite removed successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }


    //corregir porque obtiene toda la info de la tabla favoritos
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getFavoritesByUser(@PathVariable Long userId) {
        try {
            User user = userService.getUserById(userId);
            if (user != null) {
                return ResponseEntity.ok(favoriteService.getFavoritesByUser(userId));
            } else {
                throw new CustomException("User not found");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    static class FavoriteRequest {
        private Long userId;
        private Long teacherId;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Long getTeacherId() {
            return teacherId;
        }

        public void setTeacherId(Long teacherId) {
            this.teacherId = teacherId;
        }
    }
}
