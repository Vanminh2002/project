package org.example.my_project.mapper;

import org.example.my_project.dto.request.AppointmentScheduleRequest;
import org.example.my_project.dto.response.AppointmentScheduleResponse;
import org.example.my_project.entities.Appointment;
import org.example.my_project.entities.AppointmentSchedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppointmentScheduleMapper {
    @Mapping(source = "appointment.id", target = "appointmentId")
    @Mapping(source = "patient.fullName", target = "patientName")
    @Mapping(source = "doctor.fullName", target = "doctorName")
    AppointmentScheduleResponse toResponse( AppointmentSchedule appointmentSchedule);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created_at", ignore = true)
    @Mapping(target = "updated_at", ignore = true)
//    @Mapping(target = "status", expression = "java(request.getStatus() != null ? AppointmentSchedule.Status.valueOf(request.getStatus().toUpperCase()) : AppointmentSchedule.Status.SCHEDULED)")
    Appointment toEntity(AppointmentScheduleRequest request);
}
