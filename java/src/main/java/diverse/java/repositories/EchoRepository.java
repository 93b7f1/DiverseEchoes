package diverse.java.repositories;

import diverse.java.domain.Echo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EchoRepository extends JpaRepository<Echo, Integer> {

    Echo findEchoByIdEcho(Integer idEcho);

    List<Echo> findAllByOrderByIdEchoDesc();

    @Query("select e FROM Echo e WHERE e.user.idUser = :idUser")
    List<Echo> findByEchoes(@Param("idUser") Integer idUser);
}
