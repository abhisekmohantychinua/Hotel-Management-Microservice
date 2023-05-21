package dev.abhisek.userservice.controller;

import dev.abhisek.userservice.entities.User;
import dev.abhisek.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity
                .ok(service
                        .createUser(user)
                );
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId) {
        return ResponseEntity
                .ok(service
                        .getUser(userId)
                );
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity
                .ok(service
                        .getAllUser()
                );
    }
}
