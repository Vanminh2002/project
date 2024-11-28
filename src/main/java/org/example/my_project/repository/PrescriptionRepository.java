package org.example.my_project.repository;

import org.example.my_project.entities.Prescriptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescriptions,Long> {
}
