package dev.abhisek.hotelservice.services;

import dev.abhisek.hotelservice.entities.Hotel;

import java.util.List;

public interface HotelServices {
    Hotel createHotel(Hotel hotel);
    Hotel getHotel(String hotelId);

    List<Hotel> getAllHotel();
}
