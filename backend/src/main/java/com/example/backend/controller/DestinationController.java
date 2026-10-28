package com.example.backend.controller;

import com.example.backend.model.Destination;
import com.example.backend.service.DestinationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DestinationController {

    private final DestinationService destinationService;
    //constructor-based dependency injection
    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @PostMapping("/addDestination")
    public boolean addDestination(@RequestBody Destination destination) {
        return destinationService.addDestination(destination);
    }
}
