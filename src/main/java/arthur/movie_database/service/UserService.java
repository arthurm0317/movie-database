package arthur.movie_database.service;

import arthur.movie_database.entities.User;
import arthur.movie_database.repositories.UserRepository;
import arthur.movie_database.service.exceptions.DatabaseException;
import arthur.movie_database.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }
    public User findById(Long id){
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ResourceNotFoundException(id));
    }
    public User insertUser(User obj){
        return repository.save(obj);
    }
    public void deleteUser(Long id){
        try {
            if (!repository.existsById(id)) {
                throw new ResourceNotFoundException("User not found with id " + id);
            }
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }
    public User updateUser(Long id, User obj){
        try {
            User entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("User not found with id " + id);
        }

    }

    private void updateData(User entity, User obj) {
        entity.setUsername(obj.getUsername());
        entity.setEmail(obj.getEmail());
    }


}
