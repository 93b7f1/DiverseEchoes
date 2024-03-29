package diverse.java.service;

import diverse.java.domain.Echo;
import diverse.java.domain.User;
import diverse.java.repositories.EchoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EchoService {

    private EchoRepository echoRepository;

    public EchoService(EchoRepository echoRepository){
        this.echoRepository = echoRepository;
    }

    public List<Echo> echoList(){
        return echoRepository.findAll();
    }

    public Echo createEcho(Echo echo){
        return echoRepository.save(echo);
    }

    public List<Echo> echoesUser(Integer idUser){
        return echoRepository.findByEchoes(idUser);

    }
    public ResponseEntity<List<Echo>>  echoesImages(){
        List<Echo> images = echoRepository.findTop5ByTypeOrderByIdEchoDesc("image");
        if(images.isEmpty()){
            return ResponseEntity.status(204).build();

        }

        return ResponseEntity.ok(images);

    }
    public Echo editarEchoSystem(Echo echo){
        Optional<Echo> echoExistente = echoRepository.findById(echo.getIdEcho());

        if (echoExistente.isEmpty()) {
            throw new IllegalArgumentException("Echo não encontrado");
        }

        Echo echoSystemAtualizado = echoExistente.get();

        if (echo.getRedirect() != null) {
            echoSystemAtualizado.setRedirect(echo.getRedirect());
        }
        if (echo.getViews() != null){
            echoSystemAtualizado.setViews(echo.getViews());
        }
        if (echo.getStream() != null){
            echoSystemAtualizado.setStream(echo.getStream());
        }

        return echoRepository.save(echoSystemAtualizado);
    }
    public Optional<Echo> buscarId(Integer id){
        Optional<Echo> busca =  echoRepository.findById(id);
        return busca;
    }
    public List<Echo> listar5(){
        return echoRepository.findTop5ByOrderByIdEchoDesc();
    }
}
