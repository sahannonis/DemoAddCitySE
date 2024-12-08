package com.example.c;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.c.model.City;
import com.example.c.repository.CityRepository;


@SpringBootApplication
public class CityApplication {

	public static void main(String[] args) {
		SpringApplication.run(CityApplication.class, args);
	}
	@Bean
	ApplicationRunner init(CityRepository repository) {
		return args -> {

			repository.save(new City("Vancouver", 300));
	        repository.save(new City("Toronto", 400));
	        repository.save(new City("Quebec", 250));
			repository.findAll().forEach(System.out::println);
		};
	}
}
