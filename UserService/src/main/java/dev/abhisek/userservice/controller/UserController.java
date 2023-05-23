package dev.abhisek.userservice.controller;

import dev.abhisek.userservice.entities.User;
import dev.abhisek.userservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity
                .ok(service
                        .createUser(user)
                );
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId) {
        return ResponseEntity
                .ok(service
                        .getUser(userId)
                );
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping
    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallBack")
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity
                .ok(service
                        .getAllUser()
                );
    }

    //creating fallback method

    public ResponseEntity<List<User>> ratingHotelFallBack(Exception exception) {
        exception.printStackTrace();
        return ResponseEntity
                .ok(List.of(User
                                .builder()
                                .build()
                        )
                );
    }


}
