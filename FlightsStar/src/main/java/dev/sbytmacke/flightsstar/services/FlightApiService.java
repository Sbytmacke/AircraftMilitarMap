package dev.sbytmacke.flightsstar.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class FlightApiService {

    private final WebClient webClient;

    public FlightApiService(WebClient.Builder webClientBuilder, @Value("${flight.api.url}") String apiUrl) {
        this.webClient = webClientBuilder.baseUrl(apiUrl)
                // En caso de requier encabezado o token para autenticacion
                //.defaultHeader("x-apikey", "sbytmacke-token")
                .build();
    }

    public String getFlightData() {
        String path = "aircraft/live/range/53/-0.694885/100";

        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(path).build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String getTotalNumberOfFlights() {
        String path = "aircraft/detail/stats";

        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(path).build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String getAllFlights() {
        String path = "aircraft/live/stats";

        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(path).build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String getMilitaryFlights() {
        String path = "aircraft/live/military";

        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(path).build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
