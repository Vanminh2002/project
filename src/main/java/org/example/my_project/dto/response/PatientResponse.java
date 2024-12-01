package org.example.my_project.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PatientResponse {
     Long id;
     String fullName;
     LocalDate birthday;
     int gender;
     String phoneNumber;
     String email;
     String address;
     LocalDate dateJoined;

     DoctorResponse doctor;
     String imageUrl;
}
