package com.ln.mial.ecommerce.app.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class UploadFile {

    private final String FOLDER = "/ecommerce-app/images/";
    private final String IMG_DEFAULT = "default.png";

    public String upload(MultipartFile multipartFile) throws IOException {

        if (multipartFile == null || multipartFile.isEmpty()) {
            return IMG_DEFAULT;
        }

        File folder = new File(FOLDER);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String fileName = UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();

        Path path = Paths.get(FOLDER + fileName);
        Files.write(path, multipartFile.getBytes());

        return fileName;
    }

    public void delete(String nameFile) {
        File file = new File(FOLDER + nameFile);
        if (file.exists()) {
            file.delete();
        }
    }
}
