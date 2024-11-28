package org.example.my_project.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MedicationResponse {
    Long id;
    String name;  // Tên thuốc
    String dosage;  // Liều dùng
    String form;  // Dạng thuốc (ví dụ: viên, siro)
    String manufacturer;  // Nhà sản xuất
    String description;  // Mô tả thuốc (nếu cần)
    String createdAt;
    String updatedAt;


    public MedicationResponse(Long id, String name, String dosage, String form, String manufacturer, String description) {
    }

    public MedicationResponse() {

    }
}
