package arthur.movie_database.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${TMDB_KEY}")
    private String tmdbApiKey;

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .baseUrl("https://api.themoviedb.org/3")
                .defaultHeader("Authorization", "Bearer " + tmdbApiKey)
                .build();
    }
}
