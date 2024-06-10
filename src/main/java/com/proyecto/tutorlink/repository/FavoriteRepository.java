package com.proyecto.tutorlink.repository;
import com.proyecto.tutorlink.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    // Método para encontrar favoritos por ID de usuario
    List<Favorite> findByUserId(Long userId);

    // Método para verificar si un profesor es favorito de un usuario específico
    @Query("SELECT COUNT(f) > 0 FROM Favorite f WHERE f.user.id = :userId AND f.teacher.id = :teacherId")
    boolean isFavorite(Long userId, Long teacherId);

    // Método para verificar si un favorito ya existe
    boolean existsByUserIdAndTeacherId(@Param("userId") Long userId, @Param("teacherId") Long teacherId);

    Optional<Favorite> findByUserIdAndTeacherId(Long userId, Long teacherId);
}

