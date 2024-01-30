package dev.sbytmacke.flightsstar.controllers;

import dev.sbytmacke.flightsstar.services.FlightApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FlightController {

    private final FlightApiService flightApiService;

    public FlightController(FlightApiService flightApiService) {
        this.flightApiService = flightApiService;
    }

    @GetMapping("/")
    public String getHome() {
        return "API HOATREST: /flight, /flights, /militaryCoor, /military, /number";
    }

    @GetMapping("/flight")
    public String getFlightData() {
        return flightApiService.getFlightData();
    }

    @GetMapping("/flights")
    public String getAllFlights() {
        return flightApiService.getAllFlights();
    }

    @GetMapping("/militaryCoor")
    public ResponseEntity<List<Map<String, Double>>> getMilitaryFlightsCoordinates() {
        String militaryFlightsJsonString = flightApiService.getMilitaryFlights();

        // Separa el JSON en objetos individuales
        String[] jsonObjects = militaryFlightsJsonString.split("},\\{");

        // Crea una lista de mapas para representar las coordenadas
        List<Map<String, Double>> coordinatesList = new ArrayList<>();

        for (String jsonObject : jsonObjects) {
            // Extrae la latitud y longitud de cada objeto
            Double lat = extractDoubleValue(jsonObject, "lat");
            Double lon = extractDoubleValue(jsonObject, "lon");

            // Crea un mapa para representar las coordenadas de este objeto
            Map<String, Double> coordinates = new HashMap<>();
            coordinates.put("lat", lat);
            coordinates.put("lon", lon);

            // Agrega el mapa a la lista
            coordinatesList.add(coordinates);
        }

        // Devuelve la lista como un objeto JSON
        return ResponseEntity.ok(coordinatesList);
    }

    private Double extractDoubleValue(String jsonString, String key) {
        int keyIndex = jsonString.indexOf("\"" + key + "\":") + key.length() + 3;
        int endIndex = jsonString.indexOf(",", keyIndex);
        if (endIndex == -1) {
            endIndex = jsonString.indexOf("}", keyIndex);
        }

        String valueString = jsonString.substring(keyIndex, endIndex).trim();
        return Double.parseDouble(valueString);
    }

    @GetMapping("/military")
    public String getMilitary() {
        return flightApiService.getMilitaryFlights();
    }

    @GetMapping("/number")
    public String getTotalNumber() {
        return flightApiService.getTotalNumberOfFlights();
    }
}
