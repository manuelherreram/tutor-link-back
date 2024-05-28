package com.proyecto.tutorlink.repository;

import com.proyecto.tutorlink.entity.Characteristic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {
    Characteristic findByName(String name);

    boolean existsByName(String name);

}
