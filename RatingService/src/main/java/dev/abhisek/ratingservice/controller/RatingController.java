package dev.abhisek.ratingservice.controller;

import dev.abhisek.ratingservice.entity.Rating;
import dev.abhisek.ratingservice.services.RatingServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rating")
public class RatingController {
    private final RatingServices services;

    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        return ResponseEntity
                .ok(services
                        .createRating(rating)
                );
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings() {
        return ResponseEntity
                .ok(services
                        .getAllRatings()
                );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId) {
        return ResponseEntity
                .ok(services
                        .getRatingsByUserId(userId)
                );
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId) {
        return ResponseEntity
                .ok(services
                        .getRatingsByHotelId(hotelId)
                );
    }

}
