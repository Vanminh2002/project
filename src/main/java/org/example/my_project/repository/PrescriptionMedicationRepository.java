package org.example.my_project.repository;

import org.example.my_project.entities.PrescriptionMedication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionMedicationRepository extends JpaRepository<PrescriptionMedication,Long> {
}
