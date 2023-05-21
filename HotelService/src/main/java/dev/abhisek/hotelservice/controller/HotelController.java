package dev.abhisek.hotelservice.controller;

import dev.abhisek.hotelservice.entities.Hotel;
import dev.abhisek.hotelservice.services.HotelServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotel")
public class HotelController {
    private final HotelServices services;

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        return ResponseEntity
                .ok(services
                        .createHotel(hotel)
                );
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId) {
        return ResponseEntity
                .ok(services
                        .getHotel(hotelId)
                );
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotel() {
        return ResponseEntity
                .ok(services
                        .getAllHotel()
                );
    }

}
