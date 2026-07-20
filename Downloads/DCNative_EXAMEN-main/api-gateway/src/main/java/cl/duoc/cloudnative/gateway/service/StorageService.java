package cl.duoc.cloudnative.gateway.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class StorageService {

    public String uploadFile(MultipartFile file) throws IOException {
        // Versión local sin AWS S3 para pruebas
        Path path = Paths.get("./uploads");
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = path.resolve(filename);
        file.transferTo(filePath.toFile());

        return "Archivo guardado localmente: " + filename;
    }
}
