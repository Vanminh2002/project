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


    @Mapping(source = "imageUrl", target = "imageUrl")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(target = "doctor.username",ignore = true)
    @Mapping(target = "doctor.userId",ignore = true)
    PatientResponse toDto(Patients entity);

    @Mapping(target = "doctor", ignore = true) // Doctor sẽ được xử lý riêng
    void updateEntityFromDto(PatientRequest dto, @MappingTarget Patients entity);
}
