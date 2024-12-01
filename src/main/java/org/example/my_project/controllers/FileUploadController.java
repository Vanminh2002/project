package org.example.my_project.controllers;

import org.example.my_project.services.FileStorageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/upload")
public class FileUploadController {
    private final FileStorageService fileStorageService;
    private static final Logger LOGGER = Logger.getLogger(FileUploadController.class.getName());
    public FileUploadController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

//    @PostMapping
//    public boolean uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
//        try {
//            fileStorageService.saveFile(file);
//            return true;
//        } catch (IOException e) {
//           LOGGER.log(Level.SEVERE,"Exception occured", e);
//
//        }
//        return false;
//    }
}
