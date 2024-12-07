package org.example.my_project.services;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.my_project.repository.PrescriptionMedicationRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PrescriptionMedicationService {

    private final PrescriptionMedicationRepository prescriptionMedicationRepository;

    public void deletePrescriptionId (long prescriptionId) {
        prescriptionMedicationRepository.deleteByPrescriptionId(prescriptionId);
    }



}
