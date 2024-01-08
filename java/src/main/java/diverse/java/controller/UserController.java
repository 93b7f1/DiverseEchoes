package diverse.java.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import diverse.java.domain.User;
import diverse.java.dto.DtoLogin;
import diverse.java.repositories.UserRepository;
import diverse.java.security.Token;
import diverse.java.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @GetMapping("/find/{idUser}")
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

    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<User> attUser(
            @RequestParam(value = "imagem", required = false) MultipartFile imagem,
            @RequestPart("usuario") String novoUserJson){

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            User usuario = objectMapper.readValue(novoUserJson, User.class);

            Optional<User> usuarioExistente = userService.userById(usuario.getIdUser());
            if (!usuarioExistente.isPresent()) {
                return ResponseEntity.status(404).build();
            }
            if (usuario.getUsername() != null && !usuario.getUsername().isEmpty()) {
                User existingUserWithUsername = userRepository.findByUsername(usuario.getUsername());
                if (existingUserWithUsername != null && !existingUserWithUsername.getIdUser().equals(usuario.getIdUser())) {
                    return ResponseEntity.status(409).build();
                }
                usuarioExistente.get().setUsername(usuario.getUsername());
            }

            User usuarioAtualizado = usuarioExistente.get();

            if (imagem != null && !imagem.isEmpty()) {
                byte[] imagemBytes = imagem.getBytes();
                String folderPath = "C:\\Users\\call1\\Desktop\\DiverseEchoes\\Archives";

                String blobName = generateUniqueBlobName(folderPath, imagem.getOriginalFilename());

                uploadImage(imagemBytes, folderPath, blobName);

                usuarioAtualizado.setUserImage(blobName);
            }

            if (usuario.getPixivUser() != null && !usuario.getPixivUser().isEmpty()) {
                usuarioAtualizado.setPixivUser(usuario.getPixivUser());
            }

            if (usuario.getEmail() != null && !usuario.getEmail().isEmpty()) {
                usuarioAtualizado.setEmail(usuario.getEmail());
            }

            if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
                usuarioAtualizado.setPassword(usuario.getPassword());
            }

            if (usuario.getUsername() != null && !usuario.getUsername().isEmpty()) {
                usuarioAtualizado.setUsername(usuario.getUsername());
            }

            if (usuario.getTwitter() != null && !usuario.getTwitter().isEmpty()) {
                usuarioAtualizado.setTwitter(usuario.getTwitter());
            }

            if (usuario.getBiography() != null && !usuario.getBiography().isEmpty()) {
                usuarioAtualizado.setBiography(usuario.getBiography());
            }

            if (usuario.getSpotify() != null && !usuario.getSpotify().isEmpty()) {
                usuarioAtualizado.setSpotify(usuario.getSpotify());
            }

            if (usuario.getSoundCloud() != null && !usuario.getSoundCloud().isEmpty()) {
                usuarioAtualizado.setSoundCloud(usuario.getSoundCloud());
            }

            if (usuario.getYoutube() != null && !usuario.getYoutube().isEmpty()) {
                usuarioAtualizado.setYoutube(usuario.getYoutube());
            }


            return ResponseEntity.status(200).body(userService.editarUsuario(usuarioAtualizado));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(400).build();

   }



    @DeleteMapping("/{idUser}")
    public ResponseEntity<Optional<User>> DeleteUser(@PathVariable Integer idUser){
        return ResponseEntity.status(200).body(userService.deleteUserById(idUser));
    }


    private String generateUniqueBlobName(String folderPath, String originalFilename) {
        int counter = 1;
        String blobName = originalFilename;
        String extension = "";

        int dotIndex = originalFilename.lastIndexOf(".");
        if (dotIndex > 0) {
            extension = originalFilename.substring(dotIndex);
            blobName = originalFilename.substring(0, dotIndex);
        }

        String newBlobName = blobName + extension;
        File file = new File(folderPath, newBlobName);

        while (file.exists()) {
            newBlobName = blobName + counter + extension;
            file = new File(folderPath, newBlobName);
            counter++;
        }

        return newBlobName;
    }

    private void uploadImage(byte[] imageBytes, String folderPath, String fileName) {
        try {
            Path imagePath = Paths.get(folderPath, fileName);
            File imageFile = imagePath.toFile();

            try (FileOutputStream fos = new FileOutputStream(imageFile)) {
                fos.write(imageBytes);
            }


            String localImagePath = imageFile.getAbsolutePath();
            System.out.println("Arquivo Salvo: " + localImagePath);


        } catch (IOException e) {
            System.out.println("Erro ao armazenar o arquivo: " + e.getMessage());
        }
    }

}
