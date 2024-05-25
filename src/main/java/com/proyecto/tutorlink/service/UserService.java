package com.proyecto.tutorlink.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.ListUsersPage;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.proyecto.tutorlink.exception.AuthenticationException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    public List<UserRecord> getAllUsers() throws FirebaseAuthException {
        List<UserRecord> users = new ArrayList<>();
        ListUsersPage page = FirebaseAuth.getInstance().listUsers(null);
        while (page != null) {
            for (UserRecord user : page.getValues()) {
                users.add(user);
            }
            page = page.getNextPage();
        }
        return users;
    }

    public UserRecord createUser(String email, String password, String firstName, String lastName) throws FirebaseAuthException {
        CreateRequest request = new CreateRequest()
                .setEmail(email)
                .setPassword(password)
                .setDisplayName(firstName + " " + lastName);

        UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);

        // Asignar rol 'USER' por defecto
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "USER");
        FirebaseAuth.getInstance().setCustomUserClaims(userRecord.getUid(), claims);

        return userRecord;
    }
    public String loginUser(String email, String password) throws AuthenticationException {
        try {
            UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(email);
            if (userRecord != null) {
                // Simula la comprobación de la contraseña si es necesario o gestiona las sesiones
                String uid = userRecord.getUid();
                // Retorna el token de sesión
                return FirebaseAuth.getInstance().createCustomToken(uid);
            } else {
                throw new AuthenticationException("No user found with the provided email.");
            }
        } catch (Exception e) {
            throw new AuthenticationException("Login failed: " + e.getMessage());
        }
    }
}

