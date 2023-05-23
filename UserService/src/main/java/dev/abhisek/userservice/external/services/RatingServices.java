package dev.abhisek.userservice.external.services;

import dev.abhisek.userservice.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingServices {
    @RequestMapping("/rating/user/{userId}")
    List<Rating> getRatingsByUserId(@PathVariable String userId);
}
