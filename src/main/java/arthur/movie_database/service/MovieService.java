package arthur.movie_database.service;

import arthur.movie_database.entities.Movies;
import arthur.movie_database.repositories.MovieRepository;
import arthur.movie_database.service.exceptions.DatabaseException;
import arthur.movie_database.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository repository;

    @Autowired
    private TMDbService tmDbService;

    public List<Movies> findAll(){
        return repository.findAll();
    }
    public Movies findById(Integer id){
        Optional<Movies> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ResourceNotFoundException(id));
    }
    public Movies insertMovie(Movies obj){
        return repository.save(obj);
    }
    public void deleteMovie(Integer id){
        try {
            if (!repository.existsById(id)) {
                throw new ResourceNotFoundException("Movie not found with id " + id);
            }
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }
    public Movies updateMovie(Integer id, Movies obj){
        try {
            Movies entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("User not found with id " + id);
        }
    }
    public Movies favoriteMovie(Long tmdbId) {
        return repository.findByTmdbId(tmdbId)
                .orElseGet(() -> {
                    String jsonResponse = tmDbService.buscarFilmePorId(tmdbId);
                    Movies newMovie = converterJsonParaMovie(jsonResponse);
                    return repository.save(newMovie);
                });
    }
    private Movies converterJsonParaMovie(String jsonResponse) {
        // Aqui você faz a conversão do JSON para um objeto Movies
        // Pode usar ObjectMapper do Jackson, por exemplo
        return null;
    }

    private void updateData(Movies entity, Movies obj) {
        entity.setName(obj.getName());
        entity.setReleaseYear(obj.getReleaseYear());
        entity.setDescription(obj.getDescription());
        entity.setImageUrl(obj.getImageUrl());
    }

    public Optional<Movies> findByTmdbId(Long tmdbId) {
        return repository.findByTmdbId(tmdbId);
    }

}
