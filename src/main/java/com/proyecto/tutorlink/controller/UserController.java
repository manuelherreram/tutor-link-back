package com.proyecto.tutorlink.controller;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.proyecto.tutorlink.exception.AuthenticationException;
import com.proyecto.tutorlink.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() throws FirebaseAuthException {
        List<UserRecord> userRecords = userService.getAllUsers();
        List<UserDto> users = userRecords.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }
//DTO TEMPORAL - NECESARIAMENTE DEBE SER UNA CLASE SEPARADA

    private UserDto convertToDto(UserRecord userRecord) {
        UserDto dto = new UserDto();
        dto.setUid(userRecord.getUid());
        dto.setEmail(userRecord.getEmail());
        dto.setDisplayName(userRecord.getDisplayName());

        // Recuperar el rol desde los custom claims
        Map<String, Object> claims = userRecord.getCustomClaims();
        if (claims.containsKey("role")) {
            dto.setRole((String) claims.get("role"));
        } else {
            dto.setRole("No role assigned");
        }

        return dto;
    }
    static class UserDto {
        private String uid;
        private String email;
        private String displayName;
        private String role;

        // Getters and Setters
        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }
    @PostMapping("/createuser")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequest request) {
        try {
            UserRecord userRecord = userService.createUser(request.getEmail(), request.getPassword(), request.getFirstName(), request.getLastName());
            return ResponseEntity.ok("User registered successfully with UID: " + userRecord.getUid());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }

    static class UserRegistrationRequest {
        private String email;
        private String password;
        private String firstName;
        private String lastName;

        // Getters and setters
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
    }

}

