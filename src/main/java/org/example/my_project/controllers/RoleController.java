package org.example.my_project.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.my_project.dto.request.PermissionRequest;
import org.example.my_project.dto.request.RoleRequest;
import org.example.my_project.dto.response.ApiResponse;
import org.example.my_project.dto.response.PermissionResponse;
import org.example.my_project.dto.response.RoleResponse;
import org.example.my_project.repository.PermissionRepository;
import org.example.my_project.services.PermissionServices;
import org.example.my_project.services.RoleServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleController {
    PermissionServices permissionServices;
    private final PermissionRepository permissionRepository;
    private final RoleServices roleServices;


    @PostMapping
    ResponseEntity<ApiResponse<RoleResponse>> create(@RequestBody RoleRequest request) {
        return ResponseEntity.ok(ApiResponse.<RoleResponse>builder()
                .data(roleServices.create(request))
                .message("Create Role Success")
                .success(true)
                .code(HttpStatus.OK.value())
                .build());
    }
    @GetMapping
    ResponseEntity<ApiResponse<List<RoleResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.<List<RoleResponse>>builder()
                .data(roleServices.getAll())
                .message("Get All Role")
                .success(true)
                .code(HttpStatus.OK.value())
                .build());
    }
    @DeleteMapping("/{role}")
    ResponseEntity<ApiResponse<Void>> delete(@PathVariable String role){
        roleServices.delete(role);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .message("Role Deleted Success ")
                .success(true)
                .code(HttpStatus.OK.value())
                .data(null)
                .build());
    }

}
