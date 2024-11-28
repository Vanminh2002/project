package org.example.my_project.repository;

import org.example.my_project.entities.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientsRepository extends JpaRepository<Patients,Long> {
}
