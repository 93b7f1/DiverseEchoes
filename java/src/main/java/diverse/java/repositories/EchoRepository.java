package diverse.java.repositories;

import diverse.java.domain.Echo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EchoRepository extends JpaRepository<Echo, Integer> {

    Echo findEchoByIdEcho(Integer idEcho);

    List<Echo> findAllByOrderByIdEchoDesc();


}
