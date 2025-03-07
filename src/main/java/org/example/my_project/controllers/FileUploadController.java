package org.example.my_project.controllers;

import org.example.my_project.services.FileStorageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;

import java.util.logging.Logger;

@RestController
@RequestMapping("/upload")
public class FileUploadController {
    private final FileStorageService fileStorageService;
    private static final Logger LOGGER = Logger.getLogger(FileUploadController.class.getName());
    public FileUploadController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }


    @GetMapping("/{entityType}/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String entityType, @PathVariable String filename) {
        try {
            Resource file = fileStorageService.loadImageAsResource(entityType, filename);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // Hoặc MediaType.IMAGE_PNG
                    .body(file);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
