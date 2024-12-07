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
    @Size(min = 2, max = 20, message = "Username is minimum 2 character and maximum 20 character ")
    String username;
    @Size(min = 8, message = "Password must be at least 8 characters")
    String password;
    LocalDate dob;
    MultipartFile imageFile;
}
