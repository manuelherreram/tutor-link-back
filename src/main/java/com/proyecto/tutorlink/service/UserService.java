package com.proyecto.tutorlink.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.ListUsersPage;
import com.google.firebase.auth.UserRecord;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    /**
     * Retrieves all users from Firebase.
     *
     * @return a list of UserRecord objects representing all users.
     * @throws FirebaseAuthException if an error occurs during the API call to Firebase.
     */
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
}
