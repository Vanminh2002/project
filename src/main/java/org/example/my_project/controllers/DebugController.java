package org.example.my_project.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Arrays;

@RestController
@RequestMapping("/test")
public class DebugController {

    @GetMapping("/files")
    public ResponseEntity<String> checkFiles() {
        File folder = new File("D:\\project\\assets\\doctor");
        if (folder.exists() && folder.isDirectory()) {
            return ResponseEntity.ok(Arrays.toString(folder.list()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Folder not found!");
    }
}

