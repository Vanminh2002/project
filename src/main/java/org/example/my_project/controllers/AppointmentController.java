package org.example.my_project.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.my_project.dto.request.AppointmentRequest;
import org.example.my_project.dto.response.ApiResponse;
import org.example.my_project.dto.response.AppointmentResponse;
import org.example.my_project.services.AppointmentServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                .body(new ApiResponse<>(true, "Appointment created successfully", response));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AppointmentResponse>> getAppointmentById(@PathVariable Long id) {
        AppointmentResponse response = appointmentServices.getAppointmentById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Appointment retrieved successfully", response));
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<AppointmentResponse>> updateAppointmentStatus(@PathVariable Long id, @RequestParam String status) {
        AppointmentResponse response = appointmentServices.updateAppointmentStatus(id, status);
        return ResponseEntity.ok(new ApiResponse<>(true, "Appointment status updated successfully", response));
    }
    @PatchMapping("/{id}/doctor")
    public ResponseEntity<ApiResponse<AppointmentResponse>> updateDoctor(
            @PathVariable Long id,
            @RequestParam Long newDoctorId) {
        AppointmentResponse response = appointmentServices.updateDoctor(id, newDoctorId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Doctor updated successfully", response));
    }
}
