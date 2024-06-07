package com.proyecto.tutorlink.controller;
import com.google.firebase.auth.UserRecord;
import com.proyecto.tutorlink.dto.UserBasicDto;
import com.proyecto.tutorlink.dto.UserDto;
import com.proyecto.tutorlink.dto.UserRegistrationRequest;
import com.proyecto.tutorlink.entity.User;
import com.proyecto.tutorlink.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        try {
            List<UserDto> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/public/createuser")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequest request) {
        try {
            UserRecord userRecord = userService.createUser(request);
            return ResponseEntity.ok("User registered successfully with UID: " + userRecord.getUid());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }
    //getuserbyId
    @GetMapping("/public/user/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        try {
            User user = userService.getUserById(userId);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.badRequest().body("User not found");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    //getuserbyUID
    @GetMapping("/public/uid/{uid}")
    public ResponseEntity<?> getUserByUID(@PathVariable String uid) {
        try {
            User user = userService.getUserByUid(uid);
            if (user != null) {
                UserBasicDto userBasicDto = userService.convertToUserBasicDto(user);  // Convertir User a UserBasicDto
                return ResponseEntity.ok(userBasicDto);
            } else {
                return ResponseEntity.badRequest().body("User not found");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

}
