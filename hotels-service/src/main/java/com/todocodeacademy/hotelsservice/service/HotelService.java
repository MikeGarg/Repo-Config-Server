package com.todocodeacademy.hotelsservice.service;

import com.todocodeacademy.hotelsservice.model.Hotel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService implements IHotelService {

    private List<Hotel> hotelsList;

    public HotelService() {
        this.hotelsList = new ArrayList<Hotel>();
    }

    @Override
    public List<Hotel> getHotelsByCityId(Long city_id) {

        List<Hotel> hotels = new ArrayList<Hotel>();

        this.loadHotels();

        for (Hotel h : hotelsList) {
            if (h.getCity_id() == city_id) {
                hotels.add(h);
            }
        }

        return hotels;
    }

    private void loadHotels() {
        if (hotelsList.isEmpty()) {
            hotelsList.add(new Hotel(1L, "Paradise", 5, 1L));
            hotelsList.add(new Hotel(2L, "Sunset", 4, 2L));
            hotelsList.add(new Hotel(3L, "Nightmare", 3, 1L));
            hotelsList.add(new Hotel(4L, "Ass", 2, 2L));
            hotelsList.add(new Hotel(5L, "Paradox", 1, 3L));
            hotelsList.add(new Hotel(6L, "Continental", 6, 1L));
        }
    }
}
