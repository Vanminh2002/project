package org.example.my_project.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Patients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String fullName;
    LocalDate birthday;
    int gender;
    String phoneNumber;
    String email;
    String address;
    LocalDate dateJoined;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
     Doctor doctor;
//    @ManyToMany
//    @JoinTable(
//            name = "patient_doctor",
//            joinColumns = @JoinColumn(name = "patient_id"),
//            inverseJoinColumns = @JoinColumn(name = "doctor_id")
//    )
//    private Set<Doctor> doctors = new HashSet<>();
}
