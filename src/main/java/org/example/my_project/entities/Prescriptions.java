package org.example.my_project.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Prescriptions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prescriptionId;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patients patient;

    private LocalDateTime prescriptionDate;

    private String medicationDetails;

//    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<PrescriptionMedication> prescriptionMedications = new ArrayList<>();

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
