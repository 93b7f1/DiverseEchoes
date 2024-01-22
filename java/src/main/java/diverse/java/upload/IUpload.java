package diverse.java.upload;


import diverse.java.service.InvalidFileExtensionException;

public interface IUpload {
     void upload(byte[] imageBytes, String folderPath, String fileName) throws InvalidFileExtensionException;
     String getFileExtension(String fileName);

     boolean isValidExtension(String fileExtension);
}
