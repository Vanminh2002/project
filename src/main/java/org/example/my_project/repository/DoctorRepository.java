package org.example.my_project.repository;

import org.example.my_project.entities.Doctor;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    List<Doctor> findByFullName(String name);
    boolean existsByFullName(String fullName);
}
