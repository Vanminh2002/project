package org.example.my_project.entities;

import jakarta.persistence.Entity;

import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
    @Id
    String name;
    String description;

    @ManyToMany(fetch = FetchType.EAGER)
    Set<Permission> permissions;
}
