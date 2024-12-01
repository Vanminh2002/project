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
    private static final String STORAGE_DIRECTORY = "D:\\project\\assets";

    public String saveFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new NullPointerException("file is empty");
        }
        var targetFile = new File(STORAGE_DIRECTORY + File.separator + file.getOriginalFilename());
        if (!Objects.equals(targetFile.getParent(),STORAGE_DIRECTORY)){
            throw new SecurityException("unable to save file");
        }
//        if(targetFile.getCanonicalPath().startsWith(new File(STORAGE_DIRECTORY).getCanonicalPath())){
//            throw new SecurityException("unsupported filename"+file.getOriginalFilename());
//        }
        Files.copy(file.getInputStream(),targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        return targetFile.getName();
    }
    public String getFileDownloadUri(String fileName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/")
                .path(fileName)
                .toUriString();
    }
}