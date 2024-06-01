package com.proyecto.tutorlink.service;
import com.proyecto.tutorlink.entity.Favorite;
import com.proyecto.tutorlink.entity.Teacher;
import com.proyecto.tutorlink.entity.User;
import com.proyecto.tutorlink.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
