package org.example.my_project.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoctorRequest {
     String fullName;
     String specialty;
     String phoneNumber;
     String email;
     MultipartFile imageFile;
     String userId;
     String username;
     String password;
}
