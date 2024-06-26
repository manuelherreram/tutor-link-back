package com.proyecto.tutorlink.repository;

import com.proyecto.tutorlink.entity.Teacher;
import com.proyecto.tutorlink.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUID(String uid);

    Optional<User> findById(Long Id);
}
