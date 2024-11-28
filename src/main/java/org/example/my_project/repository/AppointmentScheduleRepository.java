package org.example.my_project.repository;

import org.example.my_project.entities.AppointmentSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentScheduleRepository extends JpaRepository<AppointmentSchedule, Long> {
}
