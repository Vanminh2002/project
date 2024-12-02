package org.example.my_project.mapper;

import org.example.my_project.dto.request.MedicationRequest;
import org.example.my_project.dto.response.MedicationResponse;
import org.example.my_project.dto.response.MedicationResponseDTO;
import org.example.my_project.entities.Medications;
import org.example.my_project.entities.PrescriptionMedication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicationMapper {
    @Mapping(source = "medication.name", target = "name")
    @Mapping(source = "medication.dosage", target = "dosage")
    @Mapping(source = "medication.form", target = "form")
    @Mapping(source = "medication.manufacturer", target = "manufacturer")
    @Mapping(source = "medication.description", target = "description")
    @Mapping(source = "quantity", target = "quantity")
    MedicationResponseDTO toResponse(PrescriptionMedication prescriptionMedication);

    List<MedicationResponseDTO> toResponseList(List<PrescriptionMedication> prescriptionMedications);

    @Mapping(source = "imageUrl", target = "imageUrl")
    MedicationResponse toResponse(Medications medication);

    Medications toEntity(MedicationRequest requestDTO);

    void updateMedication(MedicationRequest request, @MappingTarget Medications medications);
}
