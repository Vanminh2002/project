package org.example.my_project.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

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


}
