package com.proyecto.tutorlink.service;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.ListUsersPage;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.proyecto.tutorlink.dto.UserDto;
import com.proyecto.tutorlink.entity.User;
import com.proyecto.tutorlink.exception.AuthenticationException;
import com.proyecto.tutorlink.repository.TeacherRepository;
import com.proyecto.tutorlink.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.proyecto.tutorlink.dto.UserRegistrationRequest;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertToUserDto).collect(Collectors.toList());
    }

    private UserDto convertToUserDto(User user) {
         return new UserDto(
                user.getUID(),
                user.getEmail(),
                user.getFirstName() + " " + user.getLastName(),
                user.getRole(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getAddress(),
                user.getCity(),
                user.getCountry()
        );
    }

    public UserRecord createUser(UserRegistrationRequest request) throws Exception {
        // Crear usuario en Firebase
        CreateRequest firebaseRequest = new CreateRequest()
                .setEmail(request.getEmail())
                .setPassword(request.getPassword())
                .setDisplayName(request.getFirstName() + " " + request.getLastName());

        UserRecord userRecord = FirebaseAuth.getInstance().createUser(firebaseRequest);

        // Asignar rol 'USER' por defecto
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "USER");
        FirebaseAuth.getInstance().setCustomUserClaims(userRecord.getUid(), claims);

        // Crear y guardar el usuario en la base de datos local
        User newUser = new User();
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(request.getPassword());  // Considera cifrar la contraseña si la vas a guardar
        newUser.setRole("USER");
        newUser.setPhone(request.getPhone());
        newUser.setAddress(request.getAddress());
        newUser.setCity(request.getCity());
        newUser.setCountry(request.getCountry());
        newUser.setUID(userRecord.getUid());

        userRepository.save(newUser);

        return userRecord;
    }


    public User addUsertoDB(User user) throws Exception {
        return userRepository.save(user);
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
    public void deleteUser(String uid) throws FirebaseAuthException {
        FirebaseAuth.getInstance().deleteUser(uid);
    }
    public void updateUser(String uid, String email, String password, String firstName, String lastName) throws FirebaseAuthException {
        UserRecord.UpdateRequest request = new UserRecord.UpdateRequest(uid)
                .setEmail(email)
                .setPassword(password)
                .setDisplayName(firstName + " " + lastName);
        FirebaseAuth.getInstance().updateUser(request);
    }
    public void setRole(String uid, String role) throws FirebaseAuthException {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        FirebaseAuth.getInstance().setCustomUserClaims(uid, claims);
    }
    public String getRole(String uid) throws FirebaseAuthException {
        return FirebaseAuth.getInstance().getUser(uid).getCustomClaims().get("role").toString();
    }
    public UserRecord getUser(String uid) throws FirebaseAuthException {
        return FirebaseAuth.getInstance().getUser(uid);
    }
    //getuserbyId
    public User getUserByUid(String uid) {
        return userRepository.findByUID(uid).orElse(null);
    }
}

