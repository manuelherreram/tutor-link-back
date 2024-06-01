package com.proyecto.tutorlink.repository;
import com.proyecto.tutorlink.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    // métodos
}
