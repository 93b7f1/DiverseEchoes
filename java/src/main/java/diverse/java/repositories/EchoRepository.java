package diverse.java.repositories;

import diverse.java.domain.Echo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EchoRepository extends JpaRepository<Echo, Integer> {

    Echo findEchoByIdEcho(Integer idEcho);

    List<Echo> findAllByOrderByIdEchoDesc();

    List<Echo> findTop5ByOrderByIdEchoDesc();

    List<Echo> findTop5ByTypeOrderByIdEchoDesc(String type);

    @Query("select e FROM Echo e WHERE e.user.idUser = :idUser")
    List<Echo> findByEchoes(@Param("idUser") Integer idUser);

    //    @Query("select e FROM Echo e WHERE e.user.idUser = :idUser and e.type = :image")
    //    List<Echo> findByEchoes(@Param("idUser") Integer idUser, @Param("image") String image);
}
