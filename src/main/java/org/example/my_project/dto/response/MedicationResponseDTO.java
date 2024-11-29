package org.example.my_project.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class MedicationResponseDTO {
    String name;  // Tên thuốc
    String dosage;  // Liều dùng
    String form;  // Dạng thuốc (ví dụ: viên, siro)
    String manufacturer;  // Nhà sản xuất
    String description;
    int quantity;


}
