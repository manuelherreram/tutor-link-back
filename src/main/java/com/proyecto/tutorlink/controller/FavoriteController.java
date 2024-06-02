package com.proyecto.tutorlink.controller;

import com.proyecto.tutorlink.dto.FavoriteRemoveRequest;
import com.proyecto.tutorlink.dto.FavoriteRequest;
import com.proyecto.tutorlink.entity.Favorite;
import com.proyecto.tutorlink.entity.Teacher;
import com.proyecto.tutorlink.entity.User;
import com.proyecto.tutorlink.exception.CustomException;
import com.proyecto.tutorlink.service.FavoriteService;
import com.proyecto.tutorlink.service.TeacherService;
import com.proyecto.tutorlink.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> removeFavorite(@RequestBody FavoriteRemoveRequest request) {
        try {
            favoriteService.removeFavorite(request.getUserId(), request.getTeacherId());
            return ResponseEntity.ok().build();
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error: " + e.getMessage());
        }
    }

    @GetMapping("/user/{userId}/teachers")
    public ResponseEntity<?> getFavoriteTeachersByUser(@PathVariable Long userId) {
        try {
            User user = userService.getUserById(userId);
            if (user != null) {
                List<Teacher> favoriteTeachers = favoriteService.getFavoriteTeachersByUser(userId);
                return ResponseEntity.ok(favoriteTeachers);
            } else {
                throw new CustomException("User not found");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

}
