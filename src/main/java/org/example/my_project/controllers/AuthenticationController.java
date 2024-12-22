package org.example.my_project.controllers;

import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.my_project.dto.request.AuthenticationRequest;
import org.example.my_project.dto.request.VerifyTokenRequest;
import org.example.my_project.dto.response.ApiResponse;
import org.example.my_project.dto.response.AuthenticationResponse;
import org.example.my_project.dto.response.VerifyTokenResponse;
import org.example.my_project.services.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    ResponseEntity<ApiResponse<AuthenticationResponse>> authenticate(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);
        return ResponseEntity.ok(ApiResponse.<AuthenticationResponse>builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .data(result)
                .build());
    }
    @PostMapping("/verify")
    ResponseEntity<ApiResponse<VerifyTokenResponse>> verifyToken (@RequestBody VerifyTokenRequest request) throws ParseException, JOSEException {
        var result = authenticationService.verifyToken(request);
        return ResponseEntity.ok(ApiResponse.<VerifyTokenResponse>builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .data(result)
                .build());
    }
}
