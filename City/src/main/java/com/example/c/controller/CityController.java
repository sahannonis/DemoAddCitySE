package com.example.c.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.c.model.City;
import com.example.c.repository.CityRepository;

@CrossOrigin(origins = "http://localhost:8082") 
@RestController
@RequestMapping("/api/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @GetMapping
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<City> getCityById(@PathVariable Long id) {
        return cityRepository.findById(id);
    }

    @PostMapping
    public City createCity(@RequestBody City city) {
        return cityRepository.save(city);
    }

    @PutMapping("/{id}")
    public City updateCity(@PathVariable Long id, @RequestBody City updatedCity) {
        return cityRepository.findById(id)
                .map(city -> {
                    city.setName(updatedCity.getName());
                    city.setParks(updatedCity.getParks());
                    return cityRepository.save(city);
                })
                .orElseGet(() -> {
                    updatedCity.setId(id);
                    return cityRepository.save(updatedCity);
                });
    }

    @DeleteMapping
    public void deleteAllCities() {
        cityRepository.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteCityById(@PathVariable Long id) {
        cityRepository.deleteById(id);
    }
}