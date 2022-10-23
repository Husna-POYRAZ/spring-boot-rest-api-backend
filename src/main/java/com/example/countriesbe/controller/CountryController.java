package com.example.countriesbe.controller;

import com.example.countriesbe.exception.CountryAlreadyExistsException;
import com.example.countriesbe.exception.CountryNotFoundException;
import com.example.countriesbe.model.Country;
import com.example.countriesbe.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/countries")
@AllArgsConstructor
public class CountryController {
/*
    private static final List<Country> countries = new ArrayList<>();
    public CountryController() {
        if (countries.isEmpty()) {
            Country country1 = new Country("TR", "Turkey", "Türkiye", 90, "AS", "Ankara", "TRY");
            Country country2 = new Country("US", "Türkiye", "Türkiye", 90, "AS", "Ankara", "TRY");

            countries.add(country1);
            countries.add(country2);
        }

   }

    @GetMapping
    public List<Country> getCountryByName() {
        Country country1 = new Country("TR", "Turkey","Türkiye", 90, "AS", "Ankara", "TRY");
        Country country2 = new Country("TR", "Turkey","Türkiye", 90, "AS", "Ankara", "TRY");

        return Arrays.asList(country1, country2);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountry(@PathVariable String id) {
        Country result = null;
        for (int i=0; i<countries.size(); i++) {
            Country country = countries.get(i);
            if (country.getId().equals(id))
                result = country;
        }
        if (result == null) {
            throw new RuntimeException("Not found Country!");
        }
        return new ResponseEntity<>(result, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountry(@PathVariable String id) {
       Country result = countries.stream()
                        .filter(c->c.getId().equals(id))
                        .findFirst()
                        .orElseThrow(()->new RuntimeException("Not Found Country"));
       return ResponseEntity<>(result, OK);
    }

*/

    private final CountryService countryService;

    @GetMapping
    public ResponseEntity<List<Country>> getCountryByName() {
        return new ResponseEntity<>(countryService.getCountries(), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountry(@PathVariable String id) {
        return new ResponseEntity<>(getCountryById(id), OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Country>> getCountryByName(@RequestParam(required = false) String name) {
        return new ResponseEntity<>(countryService.getCountryByName(name), OK);
    }

    @PostMapping
    public ResponseEntity<Country> createCountry(@RequestBody Country newCountry) {
        return new ResponseEntity<>(countryService.createCountry(newCountry), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCountry(@RequestBody String id, @RequestBody Country newCountry) {
        countryService.updateCountry(id, newCountry);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable String id) {
        countryService.deleteCountry(id);
        return new ResponseEntity<>(OK);
    }

    private Country getCountryById(String id) {
        return countryService.getCountryById(id);

        /*
        return countries.stream()
                .filter(Country -> Country.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not found Country!"));
        */

    }

    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<String> handleCountryNotFoundException(CountryNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }

    @ExceptionHandler(CountryAlreadyExistsException.class)
    public ResponseEntity<String> handleCountryAlreadyExistsException(CountryAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), CONFLICT);
    }
}
