package com.example.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Country is required")
    @Size(max = 100, message = "Country must be at most 100 characters")
    private String country;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Capital is required")
    @Size(max = 100, message = "Capital must be at most 100 characters")
    private String capital;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Region is required")
    @Size(max = 100, message = "Region must be at most 100 characters")
    private String region;

    @Column(nullable = false)
    @NotNull(message = "Population is required")
    @Positive(message = "Population must be positive")
    private Long population;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Currency is required")
    @Size(max = 100, message = "Currency must be at most 100 characters")
    private String currency;

    @Column(nullable = false, length = 500)
    @NotBlank(message = "Flag URL is required")
    @Size(max = 500, message = "Flag URL must be at most 500 characters")
    private String flagUrl;

    protected Destination() {
    }

    public Destination(String country, String capital, String region, Long population, String currency, String flagUrl) {
        this.country = country;
        this.capital = capital;
        this.region = region;
        this.population = population;
        this.currency = currency;
        this.flagUrl = flagUrl;
    }

    public Long getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getFlagUrl() {
        return flagUrl;
    }

    public void setFlagUrl(String flagUrl) {
        this.flagUrl = flagUrl;
    }
}
