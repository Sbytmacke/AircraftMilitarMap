package dev.sbytmacke.flightsstar.repositories;

import dev.sbytmacke.flightsstar.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    // Spring Data JPA proporciona automáticamente las operaciones básicas de CRUD
    // Aunque se pueden hacer consultas personalizadas
}
