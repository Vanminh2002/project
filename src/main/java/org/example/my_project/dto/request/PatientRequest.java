package org.example.my_project.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PatientRequest {
     String fullName;
     LocalDate birthday;
     int gender;
     String phoneNumber;
     String email;
     String address;
     Long doctor_id;
     MultipartFile imageFile;
     String userId;
     String username;
     String password;
}
