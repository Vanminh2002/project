package org.example.my_project.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentRequest {
    Long patientId;
    Long doctorId;
    LocalDateTime appointmentTime;
    String status;
    MultipartFile imageFile;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate date;
    @JsonFormat(pattern = "HH:mm:ss")
    LocalTime time;
}
