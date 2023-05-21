package dev.abhisek.hotelservice.services.impl;

import dev.abhisek.hotelservice.entities.Hotel;
import dev.abhisek.hotelservice.exception.ResourceNotFoundException;
import dev.abhisek.hotelservice.repository.HotelRepository;
import dev.abhisek.hotelservice.services.HotelServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelServicesImpl implements HotelServices {

    private final HotelRepository repository;

    @Override
    public Hotel createHotel(Hotel hotel) {
        hotel.setHotelId(UUID
                .randomUUID()
                .toString()
        );
        return repository
                .save(hotel);
    }

    @Override
    public Hotel getHotel(String hotelId) {
        return repository
                .findById(hotelId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("hotel with given id not found !!" + hotelId)
                );
    }

    @Override
    public List<Hotel> getAllHotel() {
        return repository
                .findAll();
    }
}
