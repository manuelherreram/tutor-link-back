package com.proyecto.tutorlink.service;
import com.proyecto.tutorlink.dto.RatingDto;
import com.proyecto.tutorlink.dto.RatingResponseDto;
import com.proyecto.tutorlink.dto.RatingStatisticsDto;
import com.proyecto.tutorlink.entity.Rating;
import com.proyecto.tutorlink.entity.Teacher;
import com.proyecto.tutorlink.entity.User;
import com.proyecto.tutorlink.repository.RatingRepository;
import com.proyecto.tutorlink.repository.TeacherRepository;
import com.proyecto.tutorlink.repository.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private UserRepository userRepository;


    public Rating addRating(RatingDto ratingDto) {
        Teacher teacher = teacherRepository.findById(ratingDto.getTeacherId()).orElseThrow(() -> new RuntimeException("Teacher not found"));
        User user = userRepository.findById(ratingDto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

        Rating rating = new Rating();
        rating.setTeacher(teacher);
        rating.setUser(user);
        rating.setRating(ratingDto.getRating());
        rating.setComment(ratingDto.getComment());

        return ratingRepository.save(rating);
    }

    public List<RatingResponseDto> getRatingsForTeacher(Long teacherId) {
        List<Rating> ratings = ratingRepository.findByTeacherId(teacherId);
        ratings.forEach(rating -> {
            Hibernate.initialize(rating.getUser());
            Hibernate.initialize(rating.getTeacher());
        });
        return ratings.stream().map(RatingResponseDto::new).collect(Collectors.toList());
    }

    public List<RatingResponseDto> getRatingsByUser(Long userId) {
        List<Rating> ratings = ratingRepository.findByUserId(userId);
        ratings.forEach(rating -> {
            Hibernate.initialize(rating.getUser());
            Hibernate.initialize(rating.getTeacher());
        });
        return ratings.stream().map(RatingResponseDto::new).collect(Collectors.toList());
    }
    public Rating updateRating(Long ratingId, RatingDto ratingDto) {
        Rating rating = ratingRepository.findById(ratingId)
                .orElseThrow(() -> new RuntimeException("Rating not found"));
        rating.setRating(ratingDto.getRating());
        rating.setComment(ratingDto.getComment());
        return ratingRepository.save(rating);
    }

    public void deleteRating(Long ratingId) {
        ratingRepository.deleteById(ratingId);
    }

    public RatingStatisticsDto getRatingsStatisticsForTeacher(Long teacherId) {
        List<Rating> ratings = ratingRepository.findByTeacherId(teacherId);
        if (ratings.isEmpty()) {
            throw new RuntimeException("No ratings found for this teacher");
        }
        double average = ratingRepository.findAverageRatingByTeacherId(teacherId);
        long count = ratingRepository.countRatingsByTeacherId(teacherId);
        List<RatingResponseDto> ratingResponseDtos = ratings.stream().map(RatingResponseDto::new).collect(Collectors.toList());
        return new RatingStatisticsDto(average, (int) count, ratingResponseDtos);
    }

}


