package arthur.movie_database.service;

import arthur.movie_database.DTO.UserDTO;
import arthur.movie_database.entities.User;
import arthur.movie_database.repositories.UserRepository;
import arthur.movie_database.service.exceptions.DatabaseException;
import arthur.movie_database.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> users = repository.findAll(pageable);
        return users.map(UserDTO::new);
    }
    public UserDTO findById(Long id){
        Optional<User> obj = repository.findById(id);
        User user = obj.orElseThrow(()-> new ResourceNotFoundException(id));
        return new UserDTO(user);
    }
    public UserDTO insertUser(UserDTO obj) {
        User user = new User();
        user.setUsername(obj.getUsername());
        user.setEmail(obj.getEmail());
        user.setImage(obj.getImageUrl());
        user.setPassword(passwordEncoder.encode(obj.getPassword()));
        user = repository.save(user);
        return new UserDTO(user);
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
