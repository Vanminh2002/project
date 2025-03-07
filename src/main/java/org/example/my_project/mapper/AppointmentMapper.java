package org.example.my_project.mapper;

import org.example.my_project.dto.request.AppointmentRequest;
import org.example.my_project.dto.request.DoctorRequest;
import org.example.my_project.dto.response.AppointmentResponse;
import org.example.my_project.entities.Appointment;
import org.example.my_project.entities.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    @Mapping(source = "patient.fullName", target = "patientName")
    @Mapping(source = "doctor.fullName", target = "doctorName")
    @Mapping(source = "imageUrl", target = "imageUrl")

    AppointmentResponse toResponse(Appointment appointment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created_at", ignore = true)
    @Mapping(target = "updated_at", ignore = true)
    @Mapping(target = "status", expression = "java(request.getStatus() != null ? Appointment.Status.valueOf(request.getStatus().toUpperCase()) : Appointment.Status.PENDING)")
    Appointment toEntity(AppointmentRequest request);


    void updateEntityFromDto(AppointmentRequest dto, @MappingTarget Appointment entity);
}
