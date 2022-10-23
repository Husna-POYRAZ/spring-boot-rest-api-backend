package com.example.countriesbe.exception;

public class CountryAlreadyExistsException extends RuntimeException {
    public CountryAlreadyExistsException(String message) {
        super(message);
    }
}
