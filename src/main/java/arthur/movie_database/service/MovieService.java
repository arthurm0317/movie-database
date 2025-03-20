package arthur.movie_database.service;

import arthur.movie_database.entities.MovieGenre;
import arthur.movie_database.entities.Movies;
import arthur.movie_database.repositories.MovieRepository;
import arthur.movie_database.service.exceptions.DatabaseException;
import arthur.movie_database.service.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            throw new ResourceNotFoundException("Movie not found with id " + id);
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
    public Optional<Movies> findByTmdbId(Long tmdbId) {
        return repository.findByTmdbId(tmdbId);
    }

    private Movies converterJsonParaMovie(String jsonResponse) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            Long tmdbId = rootNode.get("id").asLong();
            String name = rootNode.get("title").asText();
            Integer releaseYear = rootNode.get("release_date").asText().isEmpty() ? null : Integer.parseInt(rootNode.get("release_date").asText().substring(0,4));
            String description = rootNode.get("overview").asText();
            List<String> genres = new ArrayList<>();
            JsonNode genresNode = rootNode.get("genres");
            if (genresNode != null && genresNode.isArray()) {
                for (JsonNode genreNode : genresNode) {
                    int genreId = genreNode.get("id").asInt();
                    genres.add(MovieGenre.getGenreNameById(genreId));
                }
            }
            String imageUrl = "https://image.tmdb.org/t/p/w500"+rootNode.get("poster_path").asText();
            return new Movies(name, releaseYear, description, imageUrl, tmdbId);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateData(Movies entity, Movies obj) {
        entity.setName(obj.getName());
        entity.setReleaseYear(obj.getReleaseYear());
        entity.setDescription(obj.getDescription());
        entity.setImageUrl(obj.getImageUrl());
    }



}
