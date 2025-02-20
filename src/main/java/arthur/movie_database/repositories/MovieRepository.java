package arthur.movie_database.repositories;

import arthur.movie_database.entities.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movies, Integer> {
}
