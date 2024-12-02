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

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/prescription")
public class PrescriptionController {
    PrescriptionService prescriptionService;

    @PostMapping
    ResponseEntity<ApiResponse<PrescriptionResponse>> createPrescription(@RequestBody PrescriptionRequest prescriptionRequest) {
        PrescriptionResponse response = prescriptionService.addPrescription(prescriptionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "Create Prescription Success", response));
    }
    @PutMapping("/{id}")
    ResponseEntity<ApiResponse<PrescriptionResponse>> updatePrescription(@PathVariable Long id, @RequestBody PrescriptionRequest prescriptionRequest) {
        PrescriptionResponse response = prescriptionService.updatePrescription(prescriptionRequest, id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Update Prescription Success", response));
    }
}
