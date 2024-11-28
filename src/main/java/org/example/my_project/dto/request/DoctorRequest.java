package org.example.my_project.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoctorRequest {
     String fullName;
     String specialty;
     String phoneNumber;
     String email;
}
