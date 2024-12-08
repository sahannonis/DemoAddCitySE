package com.example.c.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.c.model.City;

public interface CityRepository extends JpaRepository<City, Long> {
}