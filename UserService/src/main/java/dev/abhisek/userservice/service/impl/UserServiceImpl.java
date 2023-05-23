package dev.abhisek.userservice.service.impl;

import dev.abhisek.userservice.entities.Rating;
import dev.abhisek.userservice.entities.User;
import dev.abhisek.userservice.exception.ResourceNotFoundException;
import dev.abhisek.userservice.external.services.HotelServices;
import dev.abhisek.userservice.external.services.RatingServices;
import dev.abhisek.userservice.repository.UserRepository;
import dev.abhisek.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    //    private final RestTemplate restTemplate;
    private final HotelServices hotelServices;
    private final RatingServices ratingServices;

    @Override
    public User createUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return repository.save(user);
    }

    @Override
    public User getUser(String userId) {
        User user = repository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("Searched User not found" + userId)
        );

        //extracting the ratings from Rating-service
//        var ratings = Arrays
//                .stream(
//                        Objects
//                                .requireNonNull(
//                                        restTemplate
//                                                .getForObject(
//                                                        "http://RATING-SERVICE/rating/user/" + user.getUserId()
//                                                        , Rating[].class
//                                                )
//                                )
//                ).toList();
        List<Rating> ratings = ratingServices.getRatingsByUserId(user.getUserId());
        //extracting the Hotel from Hotel-service
        ratings = ratings.stream().peek(rating -> rating
                .setHotel(hotelServices
                        .getHotel(rating.getHotelId())
                )).toList();
        user.setRatings(ratings);

        return user;

    }

    @Override
    public List<User> getAllUser() {
        List<User> users = repository.findAll();
        users = users.stream().peek(user -> {
            //extracting the ratings from Rating-service
//            var ratings = Arrays
//                    .stream(
//                            Objects
//                                    .requireNonNull(
//                                            restTemplate
//                                                    .getForObject(
//                                                            "http://RATING-SERVICE/rating/user/" + user.getUserId()
//                                                            , Rating[].class
//                                                    )
//                                    )
//                    ).toList();
//      Dropped idea to get rating by using restTemplate because it is boring and I have not created configuration for restTemplate interceptor


            //extracting the Hotel from Hotel-service
            List<Rating> ratings = ratingServices.getRatingsByUserId(user.getUserId());
            //extracting the Hotel from Hotel-service
            ratings = ratings
                    .stream()
                    .peek(rating -> {
                        rating
                                .setHotel(hotelServices
                                        .getHotel(rating.getHotelId()));

                    }).toList();
            user.setRatings(ratings);

        }).toList();


        return users;
    }
}
