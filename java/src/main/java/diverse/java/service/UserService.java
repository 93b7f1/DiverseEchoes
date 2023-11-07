package diverse.java.service;

import diverse.java.domain.User;
import diverse.java.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public List<User> listUsers(){
        return userRepository.findAll();
    }

    public User createUser(User user){
        if (userRepository.existsByUsername(user.getUsername())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"User exists");
        }
        if (userRepository.existsByEmail(user.getEmail())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Email exists");
        }

        return userRepository.save(user);

    }

    public Optional<User> userById(Integer userId){
        return userRepository.findByIdUser(userId);
    }

}
