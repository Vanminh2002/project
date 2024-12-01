package org.example.my_project.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE)

public class PrescriptionMedicationId implements Serializable {
    long prescription;
    long medication;
    public PrescriptionMedicationId() {}

    public PrescriptionMedicationId(Long prescription, Long medication) {
        this.prescription = prescription;
        this.medication = medication;
    }

    // Getters and Setters

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrescriptionMedicationId that = (PrescriptionMedicationId) o;
        return Objects.equals(prescription, that.prescription) &&
                Objects.equals(medication, that.medication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prescription, medication);
    }
}
