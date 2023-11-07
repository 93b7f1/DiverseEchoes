package diverse.java.controller;

import diverse.java.domain.Echo;
import diverse.java.service.EchoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Echo>> listEchoes(){
        return ResponseEntity.status(200).body(echoService.echoList());
    }

    @PostMapping
    public ResponseEntity<Echo> createEcho(@RequestBody Echo echo){
        return ResponseEntity.status(201).body(echoService.createEcho(echo));
    }
}
