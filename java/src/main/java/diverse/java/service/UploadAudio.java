package diverse.java.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UploadAudio implements IUpload {
    @Override
    public void upload(byte[] imageBytes, String folderPath, String fileName) throws InvalidFileExtensionException {
        String fileExtension = getFileExtension(fileName);
        if (!isValidExtension(fileExtension)) {
            throw new InvalidFileExtensionException("Extensão de arquivo inválida. Apenas mp3 é permitido.");
        }

        try {
            Path imagePath = Paths.get(folderPath, fileName);
            File imageFile = imagePath.toFile();

            try (FileOutputStream fos = new FileOutputStream(imageFile)) {
                fos.write(imageBytes);
            }

            String localImagePath = imageFile.getAbsolutePath();
            System.out.println("Música salva: " + localImagePath);

        } catch (IOException e) {
            System.out.println("Erro ao armazenar música: " + e.getMessage());
        }
    }

    @Override
    public String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex == -1) {
            return "";
        }
        return fileName.substring(lastDotIndex + 1).toLowerCase();
    }

    @Override
    public boolean isValidExtension(String fileExtension) {
        return fileExtension.equals("mp3");
    }
}
