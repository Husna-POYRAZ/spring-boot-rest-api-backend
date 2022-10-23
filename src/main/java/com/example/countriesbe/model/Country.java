package com.example.countriesbe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    @Id
    private String id;
    private String name;
    private String nativeName;
    private int phoneCode;
    private String continent;
    private String capital;
    private String currency;
}
