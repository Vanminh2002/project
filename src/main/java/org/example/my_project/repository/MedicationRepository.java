package org.example.my_project.repository;

import org.example.my_project.entities.Medications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends JpaRepository<Medications, Long> {

    boolean existsByName(String name);
}
