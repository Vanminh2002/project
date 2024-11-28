package org.example.my_project.repository;

import org.example.my_project.entities.Doctor;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    boolean existsByFullName(String fullName);
}
