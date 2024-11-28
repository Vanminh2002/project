package org.example.my_project.mapper;

import org.example.my_project.dto.request.MedicationRequest;
import org.example.my_project.dto.response.MedicationResponse;
import org.example.my_project.entities.Medications;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MedicationMapper {

    MedicationResponse toResponse(Medications medication);

    Medications toEntity(MedicationRequest requestDTO);

    void updateMedication(MedicationRequest request, @MappingTarget Medications medications);
}
