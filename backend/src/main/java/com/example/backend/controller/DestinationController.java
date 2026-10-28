package com.example.backend.controller;

import com.example.backend.model.Destination;
import com.example.backend.service.DestinationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DestinationController {

    private final DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping("/destinations")
    public List<Destination> getAllDestinations() {
        return destinationService.getAllDestinations();
    }

    @GetMapping("/destinations/search")
    public List<Destination> searchDestinations(@RequestParam String country) {
        return destinationService.searchDestinationsByCountry(country);
    }

    @PostMapping("/addDestination")
    public Destination addDestination(@RequestBody Destination destination) {
        return destinationService.addDestination(destination);
    }

    @PostMapping("/addDestinations")
    public List<Destination> addDestinations(@RequestBody List<Destination> destinations) {
        return destinationService.addDestinations(destinations);
    }

    @DeleteMapping("/deleteDestination/{id}")
    public ResponseEntity<String> deleteDestination(@PathVariable Long id) {
        destinationService.deleteDestinationById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Destination deleted successfully");
    }
}
