package dev.abhisek.ratingservice.services.impl;

import dev.abhisek.ratingservice.entity.Rating;
import dev.abhisek.ratingservice.repository.RatingRepository;
import dev.abhisek.ratingservice.services.RatingServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingServices {
    private final RatingRepository repository;


    @Override
    public Rating createRating(Rating rating) {
        rating.setRatingId(UUID
                .randomUUID()
                .toString()
        );
        return repository
                .save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return repository
                .findAll();
    }

    @Override
    public List<Rating> getRatingsByUserId(String userId) {
        return repository
                .findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingsByHotelId(String hotelId) {
        return repository
                .findByHotelId(hotelId);
    }
}
