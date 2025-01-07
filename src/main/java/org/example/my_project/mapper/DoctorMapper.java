package org.example.my_project.mapper;

import org.example.my_project.dto.request.DoctorRequest;
import org.example.my_project.dto.response.DoctorResponse;
import org.example.my_project.entities.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")

public interface DoctorMapper {


    Doctor toEntity(DoctorRequest dto);


    @Mapping(source = "imageUrl", target = "imageUrl")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.id", target = "userId")
    DoctorResponse toDto(Doctor entity);

    void updateEntityFromDto(DoctorRequest dto, @MappingTarget Doctor entity);
}
