package diverse.java.repositories;

import diverse.java.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsernameOrEmail(String username,String email);
    User findByUsername(String username);

    Optional<User> findByIdUser(Integer idUser);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    @Transactional
    @Modifying

    @Query("DELETE FROM User u WHERE u.idUser = :idUser")
    Optional<User> deletarConta(@Param("idUser") Integer idUser);

}

