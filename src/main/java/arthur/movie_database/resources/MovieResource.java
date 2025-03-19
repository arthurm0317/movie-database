package arthur.movie_database.resources;
import arthur.movie_database.entities.Movies;
import arthur.movie_database.service.MovieService;
import arthur.movie_database.service.TMDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {
    private final TMDbService tmdbService;
    private MovieService movieService;

    public MovieResource(TMDbService tmdbService, MovieService movieService) {
        this.tmdbService = tmdbService;
        this.movieService = movieService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Movies> findById(@PathVariable Integer id){
        Movies obj = movieService.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    @GetMapping(value = "/search")
    public ResponseEntity <String> searchMovie(@RequestParam String title){
        return ResponseEntity.ok(tmdbService.buscarFilmePorTitulo(title));
    }
    @GetMapping(value = "/tmdb/{tmdbId}")
    public ResponseEntity<String> findByTmdbId(@PathVariable Long tmdbId) {
        return ResponseEntity.ok().body(tmdbService.buscarFilmePorId(tmdbId));
    }
    @GetMapping(value = "/popular")
    public ResponseEntity <String> popularMovies(){
        return ResponseEntity.ok(tmdbService.filmesPopulares());
    }

    @GetMapping
    public ResponseEntity<List<Movies>> findAll() {
        List<Movies> list = movieService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Movies> insertMovie(@RequestBody Movies obj){
        obj = movieService.insertMovie(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Integer id){
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Movies> updateMovie(@PathVariable Integer id, @RequestBody Movies obj){
        obj = movieService.updateMovie(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
