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
    String imageUrl;
    LocalDate dateJoined;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    Doctor doctor;

    @JsonIgnore
    @OneToMany(mappedBy = "patient")
    List<Prescriptions> prescriptions;
    @OneToOne
    @JoinColumn(name = "user_id")
    User user;
//    @ManyToMany
//    @JoinTable(
//            name = "patient_doctor",
//            joinColumns = @JoinColumn(name = "patient_id"),
//            inverseJoinColumns = @JoinColumn(name = "doctor_id")
//    )
//    private Set<Doctor> doctors = new HashSet<>();
}
