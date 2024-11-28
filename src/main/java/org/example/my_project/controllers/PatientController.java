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
    ResponseEntity<ApiResponse<PatientResponse>> createPatient(@RequestBody PatientRequest patientRequest) {
        PatientResponse patient = patientService.createPatient(patientRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "Create Patient Success ", patient));
    }
    @GetMapping("/{id}")

    ResponseEntity<ApiResponse<PatientResponse>> getById(@PathVariable Long id){
        PatientResponse response = patientService.getPatientById(id);
        return ResponseEntity.ok(new ApiResponse<>(true,"Patient",response));
    }

    @GetMapping
    ResponseEntity<ApiResponse<List<PatientResponse>>> getAll(){
        List<PatientResponse> response = patientService.getAllPatient();
        return ResponseEntity.ok(new ApiResponse<>(true,"Patient retrieved successfully",response));
    }
    @PutMapping("/{id}")
    ResponseEntity<ApiResponse<PatientResponse>> updateDoctor(@PathVariable Long id, @RequestBody PatientRequest patientRequest) {
        PatientResponse response = patientService.updatePatient(id, patientRequest);
        return ResponseEntity.ok(new ApiResponse<>(true, "Update Successfully", response));
    }
    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<Void>> deleteDoctor(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Patient deleted successfully", null));
    }
}
