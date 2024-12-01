package org.example.my_project.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PrescriptionRequest {
    Long patientId;
    Long doctorId;
//    LocalDateTime prescriptionDate;
    String medicationDetails;
    List<MedicationRequestDTO> medications;
}
