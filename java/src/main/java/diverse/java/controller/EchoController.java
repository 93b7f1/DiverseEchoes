package diverse.java.controller;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import diverse.java.domain.Echo;
import diverse.java.service.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/echoes")
public class EchoController {

   private EchoService echoService;
   public UploadImage uploadImage = new UploadImage();
   public UploadAudio uploadAudio = new UploadAudio();
   public UploadVideo uploadVideo = new UploadVideo();

    public EchoController(EchoService echoService) {
        this.echoService = echoService;

    }
    @GetMapping("last5-images")
    public ResponseEntity <List<Echo>> listImages(){
        return echoService.echoesImages();
    }
    @GetMapping
    public ResponseEntity<List<Echo>> listEchoes(){
        return ResponseEntity.status(200).body(echoService.echoList());
    }
    @GetMapping("echoes-user/{idUser}")
    public ResponseEntity<List<Echo>> listEchoesUser(@PathVariable Integer idUser){
        return ResponseEntity.status(200).body(echoService.echoesUser(idUser));
    }
    @GetMapping("last5")
    public ResponseEntity<List<Echo>> listar5(){
        return ResponseEntity.status(200).body(echoService.listar5());
    }
    @PostMapping(value="upload-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Echo> criarEchoImage(
            @RequestParam("auxPart" ) MultipartFile imagem,
            @RequestPart("novoEcho") String novoEchoJson
    ) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Echo novoEcho = objectMapper.readValue(novoEchoJson, Echo.class);

            if (!imagem.isEmpty()) {
                try {
                    byte[] imagemBytes = imagem.getBytes();

                    String folderPath = "C:\\Users\\call1\\Desktop\\DiverseEchoes\\Archives";

                    String blobName = generateUniqueBlobName(folderPath, imagem.getOriginalFilename());

                    try{
                        uploadImage.upload(imagemBytes, folderPath, blobName);

                    }catch (InvalidFileExtensionException e){
                        System.out.println(e.getMessage());
                        return ResponseEntity.status(400).build();

                    }
                    novoEcho.setAuxPart(blobName);

                    return ResponseEntity.status(201).body(echoService.createEcho(novoEcho));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return ResponseEntity.status(400).build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(400).build();
    }
    @PostMapping(value="upload-audio", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Echo> criarEchoAudio(
            @RequestParam("imagem" ) MultipartFile imagem,
            @RequestPart("auxPart" ) MultipartFile auxPart,
            @RequestPart("novoEcho") String novoEchoJson

    ) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Echo novoEcho = objectMapper.readValue(novoEchoJson, Echo.class);

            if (!imagem.isEmpty() && !auxPart.isEmpty()) {
                try {
                    byte[] imagemBytes = imagem.getBytes();
                    byte[] auxBytes = auxPart.getBytes();

                    String folderPath = "C:\\Users\\call1\\Desktop\\DiverseEchoes\\Archives";

                    String blobName = generateUniqueBlobName(folderPath, imagem.getOriginalFilename());
                    String blobName2 = generateUniqueBlobName(folderPath, auxPart.getOriginalFilename());

                    try{
                        uploadImage.upload(imagemBytes, folderPath, blobName);

                    }catch (InvalidFileExtensionException e){
                        System.out.println(e.getMessage());
                        return ResponseEntity.status(400).build();

                    }


                    try {
                        uploadAudio.upload(auxBytes, folderPath, blobName2);
                    } catch (InvalidFileExtensionException e) {
                        System.out.println(e.getMessage());
                        return ResponseEntity.status(400).build();
                    }
                    novoEcho.setEchoImage(blobName);
                    novoEcho.setAuxPart(blobName2);

                    return ResponseEntity.status(201).body(echoService.createEcho(novoEcho));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return ResponseEntity.status(400).build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(400).build();
    }
    @PatchMapping("/streams/{id}")
    public ResponseEntity<Echo> attStream(@PathVariable Integer id, @RequestBody Echo echoSystem) {
        Optional<Echo> echoSystemExistente = echoService.buscarId(id);

        if (echoSystemExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Echo echoSystemAtualizado = echoSystemExistente.get();

        if (echoSystem.getStream() != null) {
            echoSystemAtualizado.setStream(echoSystem.getStream());
        }

        try {
            Echo echoSystemEditado = echoService.editarEchoSystem(echoSystemAtualizado);
            return ResponseEntity.ok(echoSystemEditado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PatchMapping("/views/{id}")
    public ResponseEntity<Echo> attViews(@PathVariable Integer id, @RequestBody Echo echoSystem) {
        Optional<Echo> echoSystemExistente = echoService.buscarId(id);

        if (echoSystemExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Echo echoSystemAtualizado = echoSystemExistente.get();

        if (echoSystem.getViews() != null) {
            echoSystemAtualizado.setViews(echoSystem.getViews());
        }

        try {
            Echo echoSystemEditado = echoService.editarEchoSystem(echoSystemAtualizado);
            return ResponseEntity.ok(echoSystemEditado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/redirects/{id}")
    public ResponseEntity<Echo> attRedirects(@PathVariable Integer id, @RequestBody Echo echoSystem) {
        Optional<Echo> echoSystemExistente = echoService.buscarId(id);

        if (echoSystemExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Echo echoSystemAtualizado = echoSystemExistente.get();

        if (echoSystem.getRedirect() != null) {
            echoSystemAtualizado.setRedirect(echoSystem.getRedirect());
        }

        try {
            Echo echoSystemEditado = echoService.editarEchoSystem(echoSystemAtualizado);
            return ResponseEntity.ok(echoSystemEditado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
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

//    private void uploadImage(byte[] imageBytes, String folderPath, String fileName) {
//        try {
//            Path imagePath = Paths.get(folderPath, fileName);
//            File imageFile = imagePath.toFile();
//
//            try (FileOutputStream fos = new FileOutputStream(imageFile)) {
//                fos.write(imageBytes);
//            }
//
//
//            String localImagePath = imageFile.getAbsolutePath();
//            System.out.println("Arquivo Salvo: " + localImagePath);
//
//
//        } catch (IOException e) {
//            System.out.println("Erro ao armazenar o arquivo: " + e.getMessage());
//        }
//    }
}
