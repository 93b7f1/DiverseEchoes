package diverse.java.repositories;

import diverse.java.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsernameOrEmail(String username,String email);
    Optional<User> findByIdUser(Integer idUser);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}

