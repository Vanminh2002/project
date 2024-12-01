package org.example.my_project.entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@IdClass(PrescriptionMedicationId.class)
@Data
public class PrescriptionMedication {
    @Id
    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private Prescriptions prescription;

    @Id
    @ManyToOne
    @JoinColumn(name = "medication_id")
    private Medications medication;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;


}