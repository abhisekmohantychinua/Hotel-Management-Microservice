package dev.abhisek.userservice.service.impl;

import dev.abhisek.userservice.entities.Hotel;
import dev.abhisek.userservice.entities.Rating;
import dev.abhisek.userservice.entities.User;
import dev.abhisek.userservice.exception.ResourceNotFoundException;
import dev.abhisek.userservice.external.services.HotelServices;
import dev.abhisek.userservice.repository.UserRepository;
import dev.abhisek.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final RestTemplate restTemplate;
    private final HotelServices hotelServices;

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
        var ratings = Arrays
                .stream(
                        Objects
                                .requireNonNull(
                                        restTemplate
                                                .getForObject(
                                                        "http://RATING-SERVICE/rating/user/" + user.getUserId()
                                                        , Rating[].class
                                                )
                                )
                ).toList();
        //extracting the Hotel from Hotel-service
        ratings = ratings.stream().peek(rating -> rating
                .setHotel(hotelServices
                        .getHOtel(rating.getHotelId())
                )).toList();
        user.setRatings(ratings);

        return user;

    }

    @Override
    public List<User> getAllUser() {
        List<User> users = repository.findAll();
        users = users.stream().peek(user -> {
            //extracting the ratings from Rating-service
            var ratings = Arrays
                    .stream(
                            Objects
                                    .requireNonNull(
                                            restTemplate
                                                    .getForObject(
                                                            "http://RATING-SERVICE/rating/user/" + user.getUserId()
                                                            , Rating[].class
                                                    )
                                    )
                    ).toList();
            //extracting the Hotel from Hotel-service
            ratings = ratings.stream().peek(rating -> rating
                    .setHotel(restTemplate
                            .getForObject("http://HOTEL-SERVICE/hotel/" + rating.getHotelId()
                                    , Hotel.class)
                    )).toList();
            user.setRatings(ratings);

        }).toList();


        return users;
    }
}
