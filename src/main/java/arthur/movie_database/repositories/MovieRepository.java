package arthur.movie_database.repositories;
import arthur.movie_database.entities.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movies, Integer> {
    Optional<Movies> findByTmdbId(Long tmdbId);
    // - Essa interface existe para que no Repository possamos usar as operações abaixo
    // - save() -> Salvar ou atualizar um objeto Movies
    // - findById() -> Buscar um Movies pelo ID
    // - findAll() -> Buscar todos os Movies
    // - deleteById() -> Excluir um Movies pelo ID
}
