package org.example.my_project.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentResponse {
    Long id;
    String patientName;
    String doctorName;
    LocalDateTime appointmentTime;
    String status;
    String imageUrl;
    LocalDateTime created_at;
    LocalDateTime updated_at;
    String date;

    String time;
}
