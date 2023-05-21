package dev.abhisek.ratingservice.services;

import dev.abhisek.ratingservice.entity.Rating;

import java.util.List;

public interface RatingServices {
    Rating createRating(Rating rating);

    List<Rating> getAllRatings();

    List<Rating> getRatingsByUserId(String userId);

    List<Rating> getRatingsByHotelId(String hotelId);
}
