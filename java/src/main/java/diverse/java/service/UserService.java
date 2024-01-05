package diverse.java.service;

import diverse.java.domain.User;
import diverse.java.repositories.UserRepository;
import diverse.java.security.Token;
import diverse.java.security.TokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
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
        String encoder = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encoder);

        return userRepository.save(user);

    }

    public Optional<User> userById(Integer userId){
        return userRepository.findByIdUser(userId);
    }

    public Optional<User> deleteUserById(Integer idUser){
        return userRepository.deletarConta(idUser);
    }

    public Token gerarToken(User usuario) {
        Optional<User> user = userRepository.findByEmail(usuario.getEmail());
        if (user.isPresent()) {
            Boolean valid = passwordEncoder.matches(usuario.getPassword(), user.get().getPassword());
            if (valid) {
                return new Token(TokenUtil.createToken(user.get()));
            }
        }
        return null;
    }
}
