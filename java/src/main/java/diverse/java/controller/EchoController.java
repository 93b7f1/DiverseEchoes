package diverse.java.controller;

import diverse.java.domain.Echo;
import diverse.java.service.EchoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/echo")
public class EchoController {

   private EchoService echoService;

    public EchoController(EchoService echoService) {
        this.echoService = echoService;

    }

    @GetMapping
    public ResponseEntity<List<Echo>> listarEchoes (){
        return ResponseEntity.status(200).body(echoService.echoList());
    }

}
