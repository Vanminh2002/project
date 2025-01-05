package org.example.my_project.controllers;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.my_project.dto.request.PermissionRequest;
import org.example.my_project.dto.request.UserRequest;
import org.example.my_project.dto.response.ApiResponse;
import org.example.my_project.dto.response.PermissionResponse;
import org.example.my_project.dto.response.UserResponse;
import org.example.my_project.repository.PermissionRepository;
import org.example.my_project.services.PermissionServices;
import org.example.my_project.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionController {
    PermissionServices permissionServices;
    private final PermissionRepository permissionRepository;


    @PostMapping
    ResponseEntity<ApiResponse<PermissionResponse>> create(@RequestBody PermissionRequest request) {
        return ResponseEntity.ok(ApiResponse.<PermissionResponse>builder()
                .data(permissionServices.create(request))
                .message("Create Permission Success")
                .success(true)
                .code(HttpStatus.OK.value())
                .build());
    }
    @GetMapping
    ResponseEntity<ApiResponse<List<PermissionResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.<List<PermissionResponse>>builder()
                .data(permissionServices.getAll())
                .message("Get All Permission")
                .success(true)
                .code(HttpStatus.OK.value())
                .build());
    }
    @DeleteMapping("/{permission}")
    ResponseEntity<ApiResponse<Void>> delete(@PathVariable String permission){
        permissionServices.delete(permission);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .message("User Deleted Success ")
                .success(true)
                .code(HttpStatus.OK.value())
                .data(null)
                .build());
    }

}
