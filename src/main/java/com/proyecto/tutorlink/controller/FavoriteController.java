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
    public ResponseEntity<?> addFavorite(@RequestBody FavoriteRequest favoriteRequest) throws CustomException {
        User user = userService.getUserByUid(favoriteRequest.getUserId());
        Teacher teacher = teacherService.getTeacherById(favoriteRequest.getTeacherId());
        if (user != null && teacher != null) {
            Favorite favorite = favoriteService.addFavorite(user, teacher);
            return ResponseEntity.ok(favorite);
        } else {
            return ResponseEntity.badRequest().body("Invalid user or teacher ID");
        }
    }

    @DeleteMapping("/remove/{favoriteId}")
    public ResponseEntity<?> removeFavorite(@PathVariable Long favoriteId) {
        favoriteService.removeFavorite(favoriteId);
        return ResponseEntity.ok("Favorite removed successfully");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getFavoritesByUser(@PathVariable String userId) {
        User user = userService.getUserByUid(userId);
        if (user != null) {
            return ResponseEntity.ok(favoriteService.getFavoritesByUser(user));
        } else {
            return ResponseEntity.badRequest().body("Invalid user ID");
        }
    }

    static class FavoriteRequest {
        private String userId;
        private Long teacherId;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
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
