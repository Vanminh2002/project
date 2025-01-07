package org.example.my_project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String fullName;
    String username;
    String password;
    String imageUrl;
    LocalDate dob;
    @ManyToMany
    Set<Role> roles;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    Patients patient;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    Doctor doctor;
}
