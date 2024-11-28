package org.example.my_project.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentScheduleRequest {
     Long appointmentId;
     Long patientId;
     Long doctorId;
     LocalDateTime appointmentTime;
     String status;
}
