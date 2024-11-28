package org.example.my_project.dto.request;

import lombok.Data;

@Data
public class MedicationRequest {
    private String name;  // Tên thuốc
    private String dosage;  // Liều dùng
    private String form;  // Dạng thuốc (ví dụ: viên, siro, tiêm, v.v.)
    private String manufacturer;  // Nhà sản xuất
    private String description;
}
