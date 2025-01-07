package org.example.my_project.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
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
     String userId;
     String username;
}
