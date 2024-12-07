package org.example.my_project.controllers;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.my_project.dto.request.UserRequest;
import org.example.my_project.dto.response.ApiResponse;
import org.example.my_project.dto.response.UserResponse;
import org.example.my_project.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;


    @PostMapping
    ResponseEntity<ApiResponse<UserResponse>> createUser(@Valid @ModelAttribute UserRequest request) {
        UserResponse response = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<UserResponse>builder()
                        .message("Create User Success ")
                        .success(true)
                        .code(HttpStatus.OK.value())
                        .data(response)
                        .build());
    }

    @GetMapping
    ResponseEntity<ApiResponse<List<UserResponse>>> getUsers() {
        List<UserResponse> response = userService.getAllUsers();
//        return ResponseEntity.ok(new ApiResponse<>(true, "Get all users", response));
        return ResponseEntity.ok(ApiResponse.<List<UserResponse>>builder()
                .message("Get all users")
                .success(true)
                .code(HttpStatus.OK.value())
                .data(response)
                .build());
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<UserResponse>> getById(@PathVariable String id) {
        UserResponse response = userService.getUserById(id);
        return ResponseEntity.ok( ApiResponse.<UserResponse>builder()
                .message("Get By id ")
                .success(true)
                .code(HttpStatus.OK.value())
                .data(response)
                .build());
    }
//
    @PutMapping("/{id}")
    ResponseEntity<ApiResponse<UserResponse>> updateUser(@Valid @PathVariable String id, @ModelAttribute UserRequest request) {
        UserResponse response = userService.updateUser(id, request);
        return ResponseEntity.ok(ApiResponse.<UserResponse>builder()
                .message("Update User Success ")
                .success(true)
                .code(HttpStatus.OK.value())
                .data(response)
                .build());
    }
//
    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .message("User Deleted Success ")
                .success(true)
                .code(HttpStatus.OK.value())
                .data(null)
                .build());
    }

}
