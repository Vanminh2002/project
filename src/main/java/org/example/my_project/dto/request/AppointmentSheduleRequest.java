package org.example.my_project.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentSheduleRequest {

    Long doctorId;
    String date;
    String time;
}
