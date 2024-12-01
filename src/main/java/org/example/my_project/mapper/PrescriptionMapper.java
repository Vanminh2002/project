package org.example.my_project.mapper;


import org.example.my_project.dto.request.PatientRequest;
import org.example.my_project.dto.request.PrescriptionRequest;
import org.example.my_project.dto.response.MedicationResponseDTO;
import org.example.my_project.dto.response.PatientResponse;
import org.example.my_project.dto.response.PrescriptionResponse;
import org.example.my_project.entities.Medications;
import org.example.my_project.entities.Patients;
import org.example.my_project.entities.PrescriptionMedication;
import org.example.my_project.entities.Prescriptions;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface PrescriptionMapper {
    @Mapping(target = "patient.id",source = "patientId")
    @Mapping(target = "doctor.id",source = "doctorId")
    Prescriptions toEntity(PrescriptionRequest dto);


    @Mapping(target = "patientId",source = "patient.id")
    @Mapping(target = "doctorId",source = "doctor.id")
    @Mapping(source = "prescriptionMedications", target = "medications")
    PrescriptionResponse toDto(Prescriptions entity);
    default List<MedicationResponseDTO> mapMedications(List<PrescriptionMedication> prescriptionMedications) {
        if (prescriptionMedications == null) {
            return new ArrayList<>();
        }
        return prescriptionMedications.stream()
                .map(pm -> {
                    Medications medication = pm.getMedication();
                    return new MedicationResponseDTO(
                            medication.getName(),
                            medication.getDosage(),
                            medication.getForm(),
                            medication.getManufacturer(),
                            medication.getDescription(),
                            pm.getQuantity());
                })
                .collect(Collectors.toList());
    }

    void updateEntityFromDto(PatientRequest dto, @MappingTarget Patients entity);

}