package diverse.java.controller;

import diverse.java.domain.User;
import diverse.java.dto.DtoLogin;
import diverse.java.repositories.UserRepository;
import diverse.java.security.Token;
import diverse.java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {

    private UserService userService;


    public UserController(UserService userService){
        this.userService = userService;
    }

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> listUsers(){
        return ResponseEntity.status(200).body(userService.listUsers());

    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.status(201).body(userService.createUser(user));

    }

    @GetMapping("/{idUser}")
    public ResponseEntity<Optional<User>> findUserById(@PathVariable Integer idUser){
        return ResponseEntity.status(200).body(userService.userById(idUser));

    }
//    @GetMapping()
//    public ResponseEntity<List<User>> listU(){
//        return ResponseEntity.status(200).body(userService.listUsers());
//
//    }
    @PostMapping("/login")
    public ResponseEntity<DtoLogin> logar(@Valid @RequestBody User usuario) {
        Optional<User> usuarioOpt = userRepository.findByEmail(usuario.getEmail());
        if (usuarioOpt.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        Token token = userService.gerarToken(usuario);
        if(token==null){
            return ResponseEntity.status(401).build();

        }

        DtoLogin logado = new DtoLogin(usuarioOpt.get().getIdUser(),usuarioOpt.get().getUsername(),token);
        return ResponseEntity.status(202).body(logado);

    }


    @DeleteMapping("/{idUser}")
    public ResponseEntity<Optional<User>> DeleteUser(@PathVariable Integer idUser){
        return ResponseEntity.status(200).body(userService.deleteUserById(idUser));
    }
}
