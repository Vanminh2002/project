package org.example.my_project.repository;

import jakarta.transaction.Transactional;
import org.example.my_project.entities.PrescriptionMedication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionMedicationRepository extends JpaRepository<PrescriptionMedication,Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM PrescriptionMedication pm WHERE pm.prescription.prescriptionId = :prescriptionId")
    void deleteByPrescriptionId(@Param("prescriptionId") Long prescriptionId);
}
