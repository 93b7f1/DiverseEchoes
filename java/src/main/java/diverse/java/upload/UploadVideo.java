package diverse.java.upload;

import diverse.java.service.InvalidFileExtensionException;
import diverse.java.upload.IUpload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UploadVideo implements IUpload {
    @Override
    public void upload(byte[] imageBytes, String folderPath, String fileName) throws InvalidFileExtensionException {
        String fileExtension = getFileExtension(fileName);
        if (!isValidExtension(fileExtension)) {
            throw new InvalidFileExtensionException("Extensão de arquivo inválida. Apenas mp4 e avi são permitidos.");
        }
        try {
            Path imagePath = Paths.get(folderPath, fileName);
            File imageFile = imagePath.toFile();

            try (FileOutputStream fos = new FileOutputStream(imageFile)) {
                fos.write(imageBytes);
            }

            String localImagePath = imageFile.getAbsolutePath();
            System.out.println("Video salvo: " + localImagePath);

        } catch (IOException e) {
            System.out.println("Erro ao armazenar video: " + e.getMessage());
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
        return fileExtension.equals("mp4")|| fileExtension.equals("avi");
    }
}
