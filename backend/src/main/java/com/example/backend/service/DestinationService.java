package com.example.backend.service;

import com.example.backend.model.Destination;

import java.util.List;

public interface DestinationService {

    Destination addDestination(Destination destination);

    List<Destination> getAllDestinations();


    List<Destination> searchDestinationsByCountry(String country);
    void deleteDestinationById(Long destinationId);
}

