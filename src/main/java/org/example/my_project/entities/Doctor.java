package org.example.my_project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String fullName;
    String specialty;
    String phoneNumber;
    String email;
    String imageUrl;
    LocalDate available_time;
    LocalDate date_joined;
    @JsonIgnore
    @OneToMany(mappedBy = "doctor")
    List<Patients> patients;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor")
    List<Prescriptions> prescriptions;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;
}
