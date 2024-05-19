package com.proyecto.tutorlink.controller;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.ListUsersPage;
import com.google.firebase.auth.UserRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class UserController {

    @GetMapping("/api/public/users")
    public List<UserRecord> getAllUsers() throws ExecutionException, InterruptedException, FirebaseAuthException {
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
    //service pendiente
}
