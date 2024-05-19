package com.proyecto.tutorlink.controller;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/public")
public class UserRoleController {

    @PutMapping("/set-user-role")
    public String setUserRole(@RequestBody UserRoleRequest request) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", request.getRole());

        try {
            FirebaseAuth.getInstance().setCustomUserClaims(request.getUid(), claims);
            return "Role '" + request.getRole() + "' set successfully for user UID: " + request.getUid();
        } catch (FirebaseAuthException e) {
            return "Failed to set role: " + e.getMessage();
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
