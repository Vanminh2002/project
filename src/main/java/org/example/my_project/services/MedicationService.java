package org.example.my_project.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.my_project.dto.request.MedicationRequest;
import org.example.my_project.dto.response.MedicationResponse;
import org.example.my_project.entities.Medications;
import org.example.my_project.mapper.MedicationMapper;
import org.example.my_project.repository.MedicationRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MedicationService {
    MedicationMapper medicationMapper;
    MedicationRepository medicationRepository;
    private final FileStorageService fileStorageService;

    public MedicationResponse createMedication(MedicationRequest request) {


//        if (medicationRepository.existsByName(request.getName())) {
//            throw new RuntimeException("Medication Existed");
//        }

        String imageUrl = null;
        if (request.getImageFile() != null) {
            try {
                // Lưu file và lấy URL
                imageUrl = fileStorageService.saveFile("medication", request.getImageFile());
            } catch (IOException e) {
                throw new RuntimeException("Error uploading file", e);
            }
        }
        // Tạo thuốc mới từ DTO
        Medications medication = medicationMapper.toEntity(request);  // Sử dụng Mapper để chuyển DTO sang Entity
        medication.setImageUrl(imageUrl);
        medication.setCreatedAt(LocalDateTime.now());
        medication.setUpdatedAt(LocalDateTime.now());

        // Lưu thuốc vào DB
        Medications savedMedication = medicationRepository.save(medication);
        return medicationMapper.toResponse(savedMedication);  // Sử dụng Mapper để chuyển Entity sang DTO
    }

    public List<MedicationResponse> getAllMedications() {
        List<Medications> medications = medicationRepository.findAll();
        return medications.stream()
                .map(medicationMapper::toResponse)  // Sử dụng Mapper để chuyển Entity sang DTO
                .collect(Collectors.toList());
    }

    public MedicationResponse getById(Long id) {
        Medications medications = medicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medication not found with id" + id));
        return medicationMapper.toResponse(medications);
    }

    public MedicationResponse updateMedication(Long id, MedicationRequest request) {
        Medications medications = medicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medication not found with id" + id));
        medicationMapper.updateMedication(request,medications);
        medications.setUpdatedAt(LocalDateTime.now());


        String newImageUrl = null;

        if (request.getImageFile() != null && !request.getImageFile().isEmpty()) {
            try {
                newImageUrl = fileStorageService.replaceFile("medication", medications.getImageUrl(), request.getImageFile());
            } catch (IOException e) {
                throw new RuntimeException("Error uploading file", e);
            }
        } else {
            newImageUrl = medications.getImageUrl();
        }


//
        medications.setImageUrl(newImageUrl);
        Medications medicationsSave = medicationRepository.save(medications);

        return  medicationMapper.toResponse(medicationsSave);


    }

    public void deleteMedication(Long id) {
        Medications medications = medicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medication not found with id" + id));
        medicationRepository.deleteById(id);
    }
}
