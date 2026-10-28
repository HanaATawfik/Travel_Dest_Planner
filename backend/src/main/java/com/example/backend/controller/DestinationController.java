package com.example.backend.controller;

import com.example.backend.model.Destination;
import com.example.backend.service.DestinationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DestinationController {

    private final DestinationService destinationService;

    public DestinationController(@Valid DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping("/destinations")
    public List<Destination> getAllDestinations() {
        return destinationService.getAllDestinations();
    }

    @GetMapping("/destinations/search")
    public List<Destination> searchDestinations(@Valid @RequestParam String country) {
        return destinationService.searchDestinationsByCountry(country);
    }

    @PostMapping("/addDestination")
    public Destination addDestination(@Valid @RequestBody Destination destination) {
        return destinationService.addDestination(destination);
    }

    @PostMapping("/addDestinations")
    public List<Destination> addDestinations(@Valid @RequestBody List<Destination> destinations) {
        return destinationService.addDestinations(destinations);
    }

    @DeleteMapping("/deleteDestination/{id}")
    public ResponseEntity<String> deleteDestination(@Valid @PathVariable Long id) {
        destinationService.deleteDestinationById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Destination deleted successfully");
    }
}
