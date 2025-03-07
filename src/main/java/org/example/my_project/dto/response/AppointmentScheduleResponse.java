package org.example.my_project.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentScheduleResponse {
    Long id;
    Long appointmentId;
    String patientName;
    String doctorName;
    LocalDateTime appointmentTime;
    String status;  // Scheduled, Completed, Cancelled, No Show
    LocalDateTime created_at;
    LocalDateTime updated_at;
}
