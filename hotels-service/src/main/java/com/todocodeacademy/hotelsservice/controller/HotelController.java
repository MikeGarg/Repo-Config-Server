package com.todocodeacademy.hotelsservice.controller;

import com.todocodeacademy.hotelsservice.model.Hotel;
import com.todocodeacademy.hotelsservice.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private IHotelService hotelService;

    @GetMapping("/{city_id}")
    public List<Hotel> getHotelsByCityIs(@PathVariable Long city_id) {
        return hotelService.getHotelsByCityId(city_id);
    }
}
