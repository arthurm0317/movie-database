package arthur.movie_database;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class MovieDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieDatabaseApplication.class, args);

		}

}
