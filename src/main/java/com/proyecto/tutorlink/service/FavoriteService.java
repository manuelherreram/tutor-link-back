package com.proyecto.tutorlink.service;
import com.proyecto.tutorlink.entity.Favorite;
import com.proyecto.tutorlink.entity.Teacher;
import com.proyecto.tutorlink.service.TeacherService;
import com.proyecto.tutorlink.entity.User;
import com.proyecto.tutorlink.exception.CustomException;
import com.proyecto.tutorlink.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private UserService userService;

    public Favorite addFavorite(User user, Teacher teacher) throws CustomException {
        // Comprueba si el profesor ya está marcado como favorito por el usuario
        boolean exists = favoriteRepository.existsByUserIdAndTeacherId(user.getId(), teacher.getId());
        if (!exists) {
            // Si no existe, crea un nuevo favorito
            Favorite favorite = new Favorite(user, teacher);
            return favoriteRepository.save(favorite);
        } else {
              throw new CustomException("This favorite already exists.");
        }
    }


    public void removeFavorite(Long userId, Long teacherId) throws CustomException {
        // Verificar si el usuario y el profesor existen
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new CustomException("User not found with ID: " + userId);
        }

        Teacher teacher = teacherService.getTeacherById(teacherId);
        if (teacher == null) {
            throw new CustomException("Teacher not found with ID: " + teacherId);
        }

        // Encontrar el favorito para eliminar
        Optional<Favorite> favorite = favoriteRepository.findByUserIdAndTeacherId(userId, teacherId);
        if (favorite.isPresent()) {
            favoriteRepository.deleteById(favorite.get().getId());
        } else {
            throw new CustomException("No favorite found for given user and teacher ID");
        }
    }
    public List<Favorite> getFavoritesByUser(Long userId) {
        return favoriteRepository.findByUserId(userId);
    }



    public Optional<Favorite> getFavorite(Long id) {
        return favoriteRepository.findById(id);
    }
    //getuserbyId
    public Favorite getFavoriteById(Long id) {
        return favoriteRepository.findById(id).orElse(null);
    }


    public List<Teacher> getFavoriteTeachersByUser(Long userId) {
        List<Favorite> favorites = favoriteRepository.findByUserId(userId);
        return favorites.stream()
                .map(Favorite::getTeacher) // Transforma cada Favorite a Teacher
                .collect(Collectors.toList());
    }

}
