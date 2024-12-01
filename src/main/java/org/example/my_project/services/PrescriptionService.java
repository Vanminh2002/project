package org.example.my_project.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.example.my_project.dto.request.MedicationRequestDTO;
import org.example.my_project.dto.request.PrescriptionRequest;
import org.example.my_project.dto.response.PrescriptionResponse;
import org.example.my_project.entities.Medications;
import org.example.my_project.entities.PrescriptionMedication;
import org.example.my_project.entities.Prescriptions;
import org.example.my_project.mapper.PrescriptionMapper;
import org.example.my_project.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PrescriptionService {
    PrescriptionRepository prescriptionRepository;
    MedicationRepository medicationRepository;

    PrescriptionMapper prescriptionMapper;
    DoctorRepository doctorRepository;
    PatientsRepository patientsRepository;
    PrescriptionMedicationService prescriptionMedicationService;
    PrescriptionMedicationRepository prescriptionMedicationRepository;

    public PrescriptionResponse addPrescription(PrescriptionRequest prescriptionRequest) {
        Prescriptions prescriptions = prescriptionMapper.toEntity(prescriptionRequest);
        prescriptions.setPrescriptionDate(LocalDateTime.now());
        prescriptions.setCreatedAt(LocalDateTime.now());
        prescriptions.setUpdatedAt(LocalDateTime.now());
        prescriptions.getPrescriptionMedications().forEach(pm -> pm.getMedication().getName());
        Prescriptions savePrescription = prescriptionRepository.save(prescriptions);


        List<PrescriptionMedication> prescriptionMedications = new ArrayList<>();
        for (MedicationRequestDTO medicationRequestDTO : prescriptionRequest.getMedications()) {
            Medications medications = medicationRepository.findById(medicationRequestDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Medication not found"));
            PrescriptionMedication prescriptionMedication = new PrescriptionMedication();
            prescriptionMedication.setPrescription(savePrescription);
            prescriptionMedication.setMedication(medications);
            prescriptionMedication.setQuantity(medicationRequestDTO.getQuantity());

            prescriptionMedications.add(prescriptionMedication);
        }
        prescriptionMedicationRepository.saveAll(prescriptionMedications);
        savePrescription.setPrescriptionMedications(prescriptionMedications);

        return prescriptionMapper.toDto(savePrescription);
    }
}
