package org.example.my_project.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.my_project.dto.request.MedicationRequest;
import org.example.my_project.dto.response.ApiResponse;
import org.example.my_project.dto.response.MedicationResponse;
import org.example.my_project.services.MedicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/medication")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MedicationController {
    MedicationService medicationService;

    @PostMapping
    ResponseEntity<ApiResponse<MedicationResponse>> createMedication(@RequestBody MedicationRequest request) {
        MedicationResponse response = medicationService.createMedication(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "Create Medication Success", response));
    }

    @GetMapping
    ResponseEntity<ApiResponse<List<MedicationResponse>>> getAll() {
        List<MedicationResponse> responses = medicationService.getAllMedications();
        return ResponseEntity.ok(new ApiResponse<>(true, "Medication Success", responses));
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<MedicationResponse>> getById(@PathVariable Long id) {

        MedicationResponse response = medicationService.getById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Medication Success", response));
    }

    @PutMapping("/{id}")
    ResponseEntity<ApiResponse<MedicationResponse>> updateMedication(@PathVariable Long id, @RequestBody MedicationRequest request) {
        MedicationResponse response = medicationService.updateMedication(id, request);

        return ResponseEntity.ok(new ApiResponse<>(true, "Update Success", response));

    }

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<Void>> deleteMedication(@PathVariable Long id) {
        medicationService.deleteMedication(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Deleted", null));
    }

}