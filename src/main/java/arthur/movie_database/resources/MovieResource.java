package arthur.movie_database.resources;


import arthur.movie_database.entities.Movies;
import arthur.movie_database.entities.User;
import arthur.movie_database.service.MovieService;
import arthur.movie_database.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {
    @Autowired
    private MovieService movieService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Movies> findById(@PathVariable Integer id){
        Movies obj = movieService.findById(id);
        return ResponseEntity.ok().body(obj);
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
