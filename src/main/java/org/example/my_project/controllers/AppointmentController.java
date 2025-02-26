package org.example.my_project.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.my_project.dto.request.AppointmentRequest;
import org.example.my_project.dto.request.AppointmentSheduleRequest;
import org.example.my_project.dto.response.ApiResponse;
import org.example.my_project.dto.response.AppointmentResponse;
import org.example.my_project.services.AppointmentServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppointmentController {
    AppointmentServices appointmentServices;

    @PostMapping
    public ResponseEntity<ApiResponse<AppointmentResponse>> createAppointment(@RequestBody AppointmentRequest request) {
        AppointmentResponse response = appointmentServices.createAppointment(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<AppointmentResponse>builder()
                        .message("Created appointment")
                        .success(true)
                        .code(HttpStatus.OK.value())
                        .data(response)
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AppointmentResponse>> getAppointmentById(@PathVariable Long id) {
        AppointmentResponse response = appointmentServices.getAppointmentById(id);
        return ResponseEntity.ok(ApiResponse.<AppointmentResponse>builder()

                .success(true)
                .code(HttpStatus.OK.value())
                .data(response)
                .build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<AppointmentResponse>> updateAppointmentStatus(@PathVariable Long id, @RequestParam String status) {
        AppointmentResponse response = appointmentServices.updateAppointmentStatus(id, status);
        return ResponseEntity.ok(ApiResponse.<AppointmentResponse>builder()
                .message("Updated appointment with status " + status)
                .success(true)
                .code(HttpStatus.OK.value())
                .data(response)
                .build());
    }

    @PatchMapping("/{id}/doctor")
    public ResponseEntity<ApiResponse<AppointmentResponse>> updateDoctor(
            @PathVariable Long id,
            @RequestParam Long newDoctorId) {
        AppointmentResponse response = appointmentServices.updateDoctor(id, newDoctorId);
        return ResponseEntity.ok(ApiResponse.<AppointmentResponse>builder()
                .message("Updated appointment with doctor " + newDoctorId)
                .success(true)
                .code(HttpStatus.OK.value())
                .data(response)
                .build());
    }


    @GetMapping("/get-by-patient/{id}")
    public ResponseEntity<ApiResponse<List<AppointmentResponse>>> findByPatient(@PathVariable Long id) {
        List<AppointmentResponse> response = appointmentServices.findByPatientId(id);
        return ResponseEntity.ok(ApiResponse.<List<AppointmentResponse>>builder()
                .success(true)
                .code(HttpStatus.OK.value())
                .data(response)
                .build());
    }


    @PutMapping("/{id}")
    ResponseEntity<ApiResponse<AppointmentResponse>> update(@PathVariable Long id, @RequestBody AppointmentSheduleRequest request) {
        AppointmentResponse response = appointmentServices.update(id, request);
        return ResponseEntity.ok(ApiResponse.<AppointmentResponse>builder()
                .success(true)
                .code(HttpStatus.OK.value())
                .data(response)
                .build());
    }
}
