package com.example.countriesbe.service;

import com.example.countriesbe.exception.CountryAlreadyExistsException;
import com.example.countriesbe.model.Country;
import com.example.countriesbe.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    public List<Country> getCountries() {
        return countryRepository.findAll();
    }

    public Country createCountry(Country newCountry) {
        Optional<Country> countryByName = countryRepository.findByName(newCountry.getName());
        if (countryByName.isPresent()) {
                throw new CountryAlreadyExistsException("Country already exists with name: " + newCountry.getName());
        }
        return countryRepository.save(newCountry);
    }

    public Country getCountryById(String id) {
        /*
        Optional<Country> byId = countryRepository.findById(id);
        if (byId.isEmpty()) {
            throw new RuntimeException("Not found Country!");
        }
        return byId.get(); */

        return countryRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Country Not Found with Id: " + id));
    }

    public List<Country> getCountryByName(String name) {
        if (name == null) {
            return countryRepository.findAll();
        }else {
            return countryRepository.findAllByName(name);
        }

    }


    public void deleteCountry(String id) {
        countryRepository.deleteById(id);
    }



    public void updateCountry(String id, Country country) {
        Country oldCountry = getCountryById(id);
        oldCountry.setName(country.getName());
        countryRepository.save(oldCountry);
    }

}
