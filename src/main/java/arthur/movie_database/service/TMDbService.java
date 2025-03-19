package arthur.movie_database.service;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class TMDbService {
    private final WebClient webClient;

    public TMDbService(WebClient webClient) {
        this.webClient = webClient;
    }

    public String buscarFilmePorTitulo(String titulo) {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/search/movie")
                            .queryParam("query", titulo)
                            .queryParam("language", "pt-BR")
                            .build())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
    }

    public String filmesPopulares() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/popular")
                        .queryParam("language", "pt-BR")
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
    public String buscarFilmePorId(Long id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/{movie_id}")
                        .queryParam("language", "pt-BR")
                        .build(id))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }


}
