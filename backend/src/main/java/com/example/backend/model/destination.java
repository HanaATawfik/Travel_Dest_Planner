package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//flag/image
@Entity  //table name (destination)
public class destination {  //if we create new dest france id =1 , new dest cairo id=2, new dest .. id=3 etc

    @Id  //column name AKA attributes
    @GeneratedValue(strategy=GenerationType.AUTO)
    private  Long id;
    private String country;
    private String capital;
    private String region;
    private Long population;
    private String currency;

   protected destination () {}

    public  destination (String country,String capital,String region,Long population,String currency)
    {
        this.country=country;
        this.capital=capital;
        this.currency=currency;
        this.population=population;
        this.region=region;
    }

    public Long getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getCapital() {
        return capital;
    }

    public String getRegion() {
        return region;
    }

    public Long getPopulation() {
        return population;
    }

    public String getCurrency() {
        return currency;
    }

}
