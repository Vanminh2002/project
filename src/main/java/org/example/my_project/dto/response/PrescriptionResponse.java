package org.example.my_project.dto.response;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.example.my_project.dto.request.MedicationRequestDTO;
import org.example.my_project.entities.Doctor;
import org.example.my_project.entities.Patients;

import java.time.LocalDateTime;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PrescriptionResponse {
    Long prescriptionId;
    Long patientId;
    Long doctorId;
    LocalDateTime prescriptionDate;
    String medicationDetails;
    List<MedicationResponseDTO> medications;
}
