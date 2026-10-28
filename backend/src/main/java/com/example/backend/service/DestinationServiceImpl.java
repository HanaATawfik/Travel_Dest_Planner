package com.example.backend.service;

import com.example.backend.model.Destination;
import com.example.backend.repository.DestinationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DestinationServiceImpl implements DestinationService {

    private final DestinationRepository destinationRepository;
    //constructor-based dependency injection
    public DestinationServiceImpl(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    @Override
    @Transactional
    public Destination addDestination(Destination destination) {
        if (destination == null) {
            throw new IllegalArgumentException("Destination cannot be null");
        }

        return destinationRepository.save(destination);
    }

    @Override
    @Transactional
    public List<Destination> addDestinations(List<Destination> destinations) {
        if (destinations == null || destinations.isEmpty()) {
            throw new IllegalArgumentException("Destination list cannot be empty");
        }

        return destinationRepository.saveAll(destinations);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }



    @Override
    @Transactional(readOnly = true)
    public List<Destination> searchDestinationsByCountry(String country) {
        if (country == null || country.trim().isEmpty()) {
            return getAllDestinations();
        }
        return destinationRepository.findByCountryContainingIgnoreCase(country.trim());
    }

    @Override
    @Transactional
    public void deleteDestinationById(Long destinationId) {
        if (destinationId == null) {
            throw new IllegalArgumentException("Destination id cannot be null");
        }
        destinationRepository.deleteById(destinationId);
    }
}
