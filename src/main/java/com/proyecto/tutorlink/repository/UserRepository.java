package com.proyecto.tutorlink.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.tutorlink.entity.User;
public interface UserRepository extends JpaRepository<User, Long> {

}
