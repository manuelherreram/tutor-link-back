package com.proyecto.tutorlink.controller;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.proyecto.tutorlink.entity.User;
import com.proyecto.tutorlink.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class UserRoleController {

    @Autowired
    private UserRepository userRepository;  // Repositorio de usuarios
    @PutMapping("/set-role")
    public ResponseEntity<?> setUserRole(@RequestBody UserRoleRequest request) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", request.getRole());

        try {
            // Establece el rol en Firebase
            FirebaseAuth.getInstance().setCustomUserClaims(request.getUid(), claims);

            // Encuentra el usuario en la base de datos local y actualiza su rol
            User user = userRepository.findByUID(request.getUid())
                    .orElseThrow(() -> new Exception("User not found with UID: " + request.getUid()));

            user.setRole(request.getRole());
            userRepository.save(user); // Guarda los cambios en la base de datos local

            return ResponseEntity.ok("Role '" + request.getRole() + "' set successfully for user UID: " + request.getUid());
        } catch (FirebaseAuthException e) {
            return ResponseEntity.badRequest().body("Failed to set role in Firebase: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to update role in local database: " + e.getMessage());
        }
    }

    static class UserRoleRequest {
        private String uid;
        private String role;

        // Getters and setters
        public String getUid() { return uid; }
        public void setUid(String uid) { this.uid = uid; }
        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
    }
}

