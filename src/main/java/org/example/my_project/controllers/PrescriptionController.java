package org.example.my_project.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.my_project.dto.request.PrescriptionRequest;
import org.example.my_project.dto.response.ApiResponse;
import org.example.my_project.dto.response.PrescriptionResponse;
import org.example.my_project.entities.Prescriptions;
import org.example.my_project.services.PrescriptionService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/prescription")
public class PrescriptionController {
    PrescriptionService prescriptionService;

}
