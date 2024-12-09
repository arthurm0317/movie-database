package arthur.movie_database.repositories;

import arthur.movie_database.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
