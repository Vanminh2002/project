package org.example.my_project.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.my_project.dto.request.PatientRequest;
import org.example.my_project.dto.response.ApiResponse;
import org.example.my_project.dto.response.PatientResponse;
import org.example.my_project.services.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PatientController {
    PatientService patientService;


    @PostMapping
    ResponseEntity<ApiResponse<PatientResponse>> createPatient(@ModelAttribute PatientRequest patientRequest) {
        PatientResponse response = patientService.createPatient(patientRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.<PatientResponse>builder()
                .message("Create Patient Success ")
                .success(true)
                .code(HttpStatus.OK.value())
                .data(response)
                .build());
    }
    @GetMapping("/{id}")

    ResponseEntity<ApiResponse<PatientResponse>> getById(@PathVariable Long id){
        PatientResponse response = patientService.getPatientById(id);
        return ResponseEntity.ok(ApiResponse.<PatientResponse>builder()
                .success(true)
                .code(HttpStatus.OK.value())
                .data(response)
                .build());
    }

    @GetMapping
    ResponseEntity<ApiResponse<List<PatientResponse>>> getAll(){
        List<PatientResponse> response = patientService.getAllPatient();
        return ResponseEntity.ok(ApiResponse.<List<PatientResponse>>builder()

                .success(true)
                .code(HttpStatus.OK.value())
                .data(response)
                .build());
    }
    @PutMapping("/{id}")
    ResponseEntity<ApiResponse<PatientResponse>> updateDoctor(@PathVariable Long id, @ModelAttribute PatientRequest patientRequest) {
        PatientResponse response = patientService.updatePatient(id, patientRequest);
        return ResponseEntity.ok(ApiResponse.<PatientResponse>builder()
                .message("Update Patient Success ")
                .success(true)
                .code(HttpStatus.OK.value())
                .data(response)
                .build());
    }
    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<Void>> deleteDoctor(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .message("Delete Patient Success ")
                .success(true)
                .code(HttpStatus.OK.value())
                .data(null)
                .build());
    }
}
