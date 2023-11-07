package diverse.java.service;

import diverse.java.domain.Echo;
import diverse.java.repositories.EchoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EchoService {

    private EchoRepository echoRepository;

    public EchoService(EchoRepository echoRepository){
        this.echoRepository = echoRepository;
    }

    public List<Echo> echoList(){
        return echoRepository.findAllByOrderByIdEchoDesc();
    }

}
