package com.proyecto.tutorlink.service;
import com.proyecto.tutorlink.entity.Favorite;
import com.proyecto.tutorlink.entity.Teacher;
import com.proyecto.tutorlink.entity.User;
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

    public Favorite addFavorite(User user, Teacher teacher) {
        Favorite favorite = new Favorite(user, teacher);
        return favoriteRepository.save(favorite);
    }

    public void removeFavorite(Long id) {
        favoriteRepository.deleteById(id);
    }

    public List<Favorite> getFavoritesByUser(User user) {
        return favoriteRepository.findAll().stream()
                .filter(favorite -> favorite.getUser().equals(user))
                .collect(Collectors.toList());
    }

    public Optional<Favorite> getFavorite(Long id) {
        return favoriteRepository.findById(id);
    }
}
