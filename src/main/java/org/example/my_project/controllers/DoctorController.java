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

import java.util.List;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DoctorController {

    DoctorService doctorService;

    @PostMapping
    public ResponseEntity<ApiResponse<DoctorResponse>> createDoctor(@ModelAttribute DoctorRequest dto) {
        DoctorResponse doctor = doctorService.createDoctor(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Doctor created successfully", doctor));
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<DoctorResponse>> getById(@PathVariable Long id) {
        DoctorResponse response = doctorService.getDoctorById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Doctor retrieved successfully", response));
    }

    @GetMapping
    ResponseEntity<ApiResponse<List<DoctorResponse>>> getAll() {
        List<DoctorResponse> response = doctorService.getAllDoctors();
        return ResponseEntity.ok(new ApiResponse<>(true, "Doctors retrieved successfully", response));
    }

    @PutMapping("/{id}")
    ResponseEntity<ApiResponse<DoctorResponse>> updateDoctor(@PathVariable Long id, @ModelAttribute DoctorRequest doctorRequest) {
        DoctorResponse response = doctorService.updateDoctor(id, doctorRequest);
        return ResponseEntity.ok(new ApiResponse<>(true, "Update Successfully", response));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<Void>> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Doctor deleted successfully", null));
    }
}
