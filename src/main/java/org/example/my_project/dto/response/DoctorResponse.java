package org.example.my_project.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.example.my_project.entities.Patients;

import java.time.LocalDate;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoctorResponse {
     Long id;
     String fullName;
     String specialty;
     String phoneNumber;
     String email;
     LocalDate available_time;
     LocalDate date_joined;
     String imageUrl;
     String userId;
     String username;
//     List<Patients> patients;
}
