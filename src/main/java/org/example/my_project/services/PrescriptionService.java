package org.example.my_project.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.example.my_project.mapper.PrescriptionMapper;
import org.example.my_project.repository.*;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PrescriptionService {
    PrescriptionRepository prescriptionRepository;
    MedicationRepository medicationRepository;
//    PrescriptionMedicationRepository prescriptionMedicationRepository;
    PrescriptionMapper prescriptionMapper;
    DoctorRepository doctorRepository;
    PatientsRepository patientsRepository;

    PrescriptionMedicationService prescriptionMedicationService;


}
