package com.example.backend.service;

import com.example.backend.model.Destination;

import java.util.List;

public interface DestinationService {
    Destination addDestination(Destination destination);
    List<Destination> fetchDestinationList();

    void deleteDestinationById(Long destinationId);
    Destination searchDestinationById(Long destinationId);
}

