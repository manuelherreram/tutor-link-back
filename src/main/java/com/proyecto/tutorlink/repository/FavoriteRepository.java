package com.proyecto.tutorlink.repository;
import com.proyecto.tutorlink.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    // métodos
    List<Favorite> findByUserId(Long userId);

}
