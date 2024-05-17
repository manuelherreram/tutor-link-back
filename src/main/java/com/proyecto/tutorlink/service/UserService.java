package com.proyecto.tutorlink.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.proyecto.tutorlink.entity.User;
import com.proyecto.tutorlink.repository.UserRepository;

import java.util.NoSuchElementException;

@Service
    public class UserService {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        public User saveUser(User user) {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            return userRepository.save(user);
        }

        public User findUserById(Long id) {
            return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
        }

        public User updateUser(Long id, User userDetails) {
            User user = findUserById(id);
            user.setName(userDetails.getName());
            user.setLastName(userDetails.getLastName());
            user.setEmail(userDetails.getEmail());
            // Codifica la contraseña
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
            return userRepository.save(user);
        }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

