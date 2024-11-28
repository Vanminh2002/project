package org.example.my_project.mapper;

import org.example.my_project.dto.request.PatientRequest;
import org.example.my_project.dto.response.PatientResponse;
import org.example.my_project.entities.Patients;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {DoctorMapper.class})
public interface PatientMapper {
    Patients toEntity(PatientRequest dto);

    PatientResponse toDto(Patients entity);

    @Mapping(target = "doctor", ignore = true) // Doctor sẽ được xử lý riêng
    void updateEntityFromDto(PatientRequest dto, @MappingTarget Patients entity);
}
