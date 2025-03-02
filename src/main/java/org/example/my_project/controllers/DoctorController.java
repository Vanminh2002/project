package org.example.my_project.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.my_project.dto.request.DoctorRequest;
import org.example.my_project.dto.response.ApiResponse;
import org.example.my_project.dto.response.DoctorResponse;
import org.example.my_project.services.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DoctorController {

    DoctorService doctorService;

    @PostMapping
    public ResponseEntity<ApiResponse<DoctorResponse>> createDoctor(@ModelAttribute DoctorRequest dto) {
        DoctorResponse response = doctorService.createDoctor(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<DoctorResponse>builder()
                        .message("Create Doctor Success ")
                        .success(true)
                        .code(HttpStatus.OK.value())
                        .data(response)
                        .build());
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<DoctorResponse>> getById(@PathVariable Long id) {
        DoctorResponse response = doctorService.getDoctorById(id);
        return ResponseEntity.ok(ApiResponse.<DoctorResponse>builder()
                .success(true)
                .code(HttpStatus.OK.value())
                .data(response)
                .build());
    }

    @GetMapping
    ResponseEntity<ApiResponse<List<DoctorResponse>>> getAll() {
        List<DoctorResponse> response = doctorService.getAllDoctors();
        return ResponseEntity.ok(ApiResponse.<List<DoctorResponse>>builder()
                .success(true)
                .code(HttpStatus.OK.value())
                .data(response)
                .build());
    }

    @PutMapping("/{id}")
    ResponseEntity<ApiResponse<DoctorResponse>> updateDoctor(@PathVariable Long id, @ModelAttribute DoctorRequest doctorRequest) {
        DoctorResponse response = doctorService.updateDoctor(id, doctorRequest);
        return ResponseEntity.ok(ApiResponse.<DoctorResponse>builder()
                .message("Update Doctor Success ")
                .success(true)
                .code(HttpStatus.OK.value())
                .data(response)
                .build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<Void>> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .message("Doctor Deleted Success ")
                .success(true)
                .code(HttpStatus.OK.value())
                .data(null)
                .build());

    }

    @GetMapping("/name/{name}")
    ResponseEntity<ApiResponse<List<DoctorResponse>>> getDoctorByName(@PathVariable String name) {
        List<DoctorResponse> response  = doctorService.findByName(name);
        return ResponseEntity.ok(ApiResponse.<List<DoctorResponse>>builder()
                .message("Doctor Find Success ")
                .success(true)
                .code(HttpStatus.OK.value())
                .data(response)
                .build());

    }
}
