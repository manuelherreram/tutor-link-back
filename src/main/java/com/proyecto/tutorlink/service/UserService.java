package com.proyecto.tutorlink.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.tutorlink.repository.UserRepository;


@Service
    public class UserService {

        @Autowired
        private UserRepository userRepository;

}

