package org.example.my_project.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileStorageService {
//    D:\project\assets
//    D:\my_project\assets
    private static final String STORAGE_DIRECTORY = "D:\\project\\assets";

//    public String saveFile(MultipartFile file) throws IOException {
//        if (file.isEmpty()) {
//            throw new NullPointerException("file is empty");
//        }
//        var targetFile = new File(STORAGE_DIRECTORY + File.separator + file.getOriginalFilename());
//        if (!Objects.equals(targetFile.getParent(), STORAGE_DIRECTORY)) {
//            throw new SecurityException("unable to save file");
//        }
//        Files.copy(file.getInputStream(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//        return targetFile.getName();
//    }

    public String saveFile(String entityType, MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new NullPointerException("file is empty");
        }
        String targetDirectory = STORAGE_DIRECTORY + File.separator + entityType;
        File dir = new File(targetDirectory);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        var targetFilePath = targetDirectory + File.separator + file.getOriginalFilename();
        File filePath = new File(targetFilePath);


//        var targetFile = new File(STORAGE_DIRECTORY + File.separator + file.getOriginalFilename());
        if (!Objects.equals(filePath.getParent(), targetDirectory)) {
            throw new SecurityException("unable to save file");
        }
        Files.copy(file.getInputStream(), filePath.toPath(), StandardCopyOption.REPLACE_EXISTING);
        return filePath.getName();
    }


    public String getFileDownloadUri(String fileName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/")
                .path(fileName)
                .toUriString();
    }

    public String replaceFile(String entityType, String oldFileName, MultipartFile file) throws IOException {
        if (oldFileName != null && !oldFileName.isEmpty()) {
            File fileOld = new File(STORAGE_DIRECTORY + File.separator + entityType + File.separator + oldFileName);
            if (fileOld.exists() && fileOld.isFile()) {
                boolean isDeleted = fileOld.delete();
                if (!isDeleted) {
                    throw new RuntimeException("Failed to delete old file" + oldFileName);
                }
            }
        }
        return saveFile(entityType,file);
    }
}