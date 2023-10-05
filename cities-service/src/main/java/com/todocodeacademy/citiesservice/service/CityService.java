package com.todocodeacademy.citiesservice.service;

import com.todocodeacademy.citiesservice.dto.CityDTO;
import com.todocodeacademy.citiesservice.model.City;
import com.todocodeacademy.citiesservice.repository.IHotelsAPI;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService implements ICityService {

    @Autowired
    private IHotelsAPI hotelsAPI;
    private List<City> cities;

    public CityService() {
        this.cities = new ArrayList<City>();
    }

    @Override
    @CircuitBreaker(name = "hotels-service", fallbackMethod = "fallbackGetCitiesHotel")
    @Retry(name = "hotels-service")
    public CityDTO getCitiesHotels(String name, String country) {

        City city = findCity(name, country);

        CityDTO dto = new CityDTO();

        dto.setId(city.getId());
        dto.setName(city.getName());
        dto.setCountry(city.getCountry());
        dto.setContinent(city.getContinent());
        dto.setState(city.getState());

        dto.setHotelsList(hotelsAPI.getHotelsByCityId(city.getId()));

        //createException();

        return dto;
    }

    public CityDTO fallbackGetCitiesHotel(Throwable throwable) {
        return new CityDTO(999999999999L, "error", "error", "error", "error", null);
    }

    public void createException() {
        throw new IllegalArgumentException("Prueba de error");
    }

    private City findCity(String name, String country) {

        this.loadCities();
        for (City c : cities) {
            if (c.getName().equals(name)) {
                if (c.getCountry().equals(country)) {
                    return c;
                }
            }
        }
        return null;
    }

    private void loadCities() {
        cities.add(new City(1L, "Cordoba", "South America", "Argentina", "Cordoba"));
        cities.add(new City(2L, "Rosario", "South America", "Argentina", "Santa Fe"));
        cities.add(new City(3L, "Mexico", "North America", "Mexico", "Mexico City"));
        cities.add(new City(4L, "Santiago", "South America", "Chile", "Santiago Metropolitan"));
        cities.add(new City(5L, "Madrid", "Europe", "Spain", "Community of Madrid"));
        cities.add(new City(6L, "Frankfurt", "Europe", "Germany", "Cordoba"));
        cities.add(new City(7L, "Valencia", "Europe", "Spain", "Valencia"));

    }
}
