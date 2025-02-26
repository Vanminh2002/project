package org.example.my_project.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    LocalDateTime appointmentTime;

    @Enumerated(EnumType.STRING)
    Status status; // Trạng thái của cuộc hẹn: Pending, Confirmed, etc.

    @Column(updatable = false)
    private LocalDateTime created_at; // Thời gian tạo bản ghi

    private LocalDateTime updated_at;
    String imageUrl;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    String date;


    String time;


    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patients patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    public enum Status {
        PENDING,
        CONFIRMED,
        COMPLETED,
        CANCELLED
    }

    // Các getter, setter, constructor
    @PrePersist
    public void prePersist() {
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updated_at = LocalDateTime.now();
    }
}
