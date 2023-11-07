package diverse.java.controller;

import diverse.java.domain.User;
import diverse.java.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<List<User>> listUsers(){
        return ResponseEntity.status(200).body(userService.listUsers());

    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.status(201).body(userService.createUser(user));

    }

    @GetMapping("/{userId}")
    public ResponseEntity<Optional<User>> findUserById(@PathVariable Integer userId){
        return ResponseEntity.status(200).body(userService.userById(userId));

    }

}
