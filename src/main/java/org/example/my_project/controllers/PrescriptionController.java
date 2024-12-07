package org.example.my_project.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.my_project.dto.request.PrescriptionRequest;
import org.example.my_project.dto.response.ApiResponse;
import org.example.my_project.dto.response.PrescriptionResponse;
import org.example.my_project.entities.Prescriptions;
import org.example.my_project.services.PrescriptionService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/prescription")
public class PrescriptionController {
    PrescriptionService prescriptionService;

    @PostMapping
    ResponseEntity<ApiResponse<PrescriptionResponse>> createPrescription(@RequestBody PrescriptionRequest prescriptionRequest) {
        PrescriptionResponse response = prescriptionService.addPrescription(prescriptionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.<PrescriptionResponse>builder()
                .message("Create Prescription Success ")
                .success(true)
                .code(HttpStatus.OK.value())
                .data(response)
                .build());

    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<PrescriptionResponse>> getById(@PathVariable Long id) {
        PrescriptionResponse response = prescriptionService.getById(id);
        return ResponseEntity.ok(ApiResponse.<PrescriptionResponse>builder()
                .success(true)
                .code(HttpStatus.OK.value())
                .data(response)
                .build());
    }

    @GetMapping
    ResponseEntity<ApiResponse<List<PrescriptionResponse>>> getAll() {
        List<PrescriptionResponse> response = prescriptionService.getAll();
        return ResponseEntity.ok(ApiResponse.<List<PrescriptionResponse>>builder()

                .success(true)
                .code(HttpStatus.OK.value())
                .data(response)
                .build());
    }

    @PutMapping("/{id}")
    ResponseEntity<ApiResponse<PrescriptionResponse>> update(@PathVariable Long id, @RequestBody PrescriptionRequest prescriptionRequest) {
        PrescriptionResponse response = prescriptionService.updatePrescription(id, prescriptionRequest);
        return ResponseEntity.ok(ApiResponse.<PrescriptionResponse>builder()
                .message("Update Prescription Success ")
                .success(true)
                .code(HttpStatus.OK.value())
                .data(response)
                .build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        prescriptionService.deletePrescription(id);
        return ResponseEntity.noContent().build();
    }
}
