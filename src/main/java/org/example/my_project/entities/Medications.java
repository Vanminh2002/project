package org.example.my_project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Medications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;  // Tên thuốc
    String dosage;  // Liều dùng
    String form;  // Dạng thuốc (ví dụ: viên, siro)
    String manufacturer;  // Nhà sản xuất
    String description;  // Mô tả thuốc (nếu cần)
    String imageUrl;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

}
