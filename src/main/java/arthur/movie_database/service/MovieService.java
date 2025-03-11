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

    private void updateData(Movies entity, Movies obj) {
        entity.setName(obj.getName());
        entity.setReleaseYear(obj.getReleaseYear());
        entity.setDescription(obj.getDescription());
        entity.setImageUrl(obj.getImageUrl());
    }

}
