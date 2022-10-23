package com.example.countriesbe.repository;

import com.example.countriesbe.model.Country;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends MongoRepository<Country, String> {
    List<Country> findAllByName(String name);
    Optional<Country> findByName(String name);
}
