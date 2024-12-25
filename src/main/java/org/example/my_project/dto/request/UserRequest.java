package org.example.my_project.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    String fullName;
    @Size(min = 2, max = 20, message = "USERNAME_INVALID")
    String username;
    @Size(min = 4, message = "PASSWORD_INVALID")
    String password;
    LocalDate dob;
    MultipartFile imageFile;
}
