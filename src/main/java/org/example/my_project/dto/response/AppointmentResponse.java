package org.example.my_project.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentResponse {
    Long id;
    String patientName;
    String doctorName;
    LocalDateTime appointmentTime;
    String status;
    LocalDateTime created_at;
    LocalDateTime updated_at;
}
