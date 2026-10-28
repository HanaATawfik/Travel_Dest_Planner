package com.example.backend.repository;

import com.example.backend.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
public interface DestinationRepository
    extends JpaRepository<Destination, Long> {
    List<Destination> findByCountryContainingIgnoreCase(String country);
}
