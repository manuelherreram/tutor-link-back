package com.proyecto.tutorlink.dto;

import java.util.List;

public class RatingStatisticsDto {
    private double averageRating;
    private int totalRatings;
    private List<RatingResponseDto> ratings;

    public RatingStatisticsDto(double averageRating, int totalRatings, List<RatingResponseDto> ratings) {
        this.averageRating = averageRating;
        this.totalRatings = totalRatings;
        this.ratings = ratings;
    }

    // Getters y setters
    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getTotalRatings() {
        return totalRatings;
    }

    public void setTotalRatings(int totalRatings) {
        this.totalRatings = totalRatings;
    }

    public List<RatingResponseDto> getRatings() {
        return ratings;
    }

    public void setRatings(List<RatingResponseDto> ratings) {
        this.ratings = ratings;
    }
}
