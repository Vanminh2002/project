package org.example.my_project.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.my_project.dto.request.DoctorRequest;
import org.example.my_project.dto.response.DoctorResponse;
import org.example.my_project.entities.Doctor;
import org.example.my_project.mapper.DoctorMapper;
import org.example.my_project.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DoctorService {
    DoctorMapper doctorMapper;
    DoctorRepository doctorRepository;
    private final FileStorageService fileStorageService;
    private static final String STORAGE_DIRECTORY = "D:\\my_project\\assets";

    public DoctorResponse createDoctor(DoctorRequest dto) {

        if (doctorRepository.existsByFullName(dto.getFullName())) {
            throw new RuntimeException("Doctor Existed");
        }
        String imageUrl = null;
        if (dto.getImageFile() != null) {
            try {
                // Lưu file và lấy URL
                imageUrl = fileStorageService.saveFile("doctor", dto.getImageFile());
            } catch (IOException e) {
                throw new RuntimeException("Error uploading file", e);
            }
        }


        Doctor doctor = doctorMapper.toEntity(dto);

        doctor.setImageUrl(imageUrl);
        Doctor savedDoctor = doctorRepository.save(doctor);

        return doctorMapper.toDto(savedDoctor);
    }


    public DoctorResponse getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
        return doctorMapper.toDto(doctor);
    }

    public List<DoctorResponse> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(doctorMapper::toDto)
                .collect(Collectors.toList());
    }

    public DoctorResponse updateDoctor(Long id, DoctorRequest doctorRequest) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));

        // Dùng mapper để cập nhật thông tin bác sĩ từ DTO vào thực thể
        doctorMapper.updateEntityFromDto(doctorRequest, doctor);


        String newImageUrl = null;

        if (doctorRequest.getImageFile() != null && !doctorRequest.getImageFile().isEmpty()) {
            try {
                newImageUrl = fileStorageService.replaceFile("doctor", doctor.getImageUrl(), doctorRequest.getImageFile());
            } catch (IOException e) {
                throw new RuntimeException("Error uploading file", e);
            }
        } else {
            newImageUrl = doctor.getImageUrl();
        }


//
        doctor.setImageUrl(newImageUrl);
        // Lưu thông tin bác sĩ đã cập nhật
        Doctor updatedDoctor = doctorRepository.save(doctor);

        // Trả về phản hồi dưới dạng DTO
        return doctorMapper.toDto(updatedDoctor);
    }

    public void deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));

        doctorRepository.deleteById(id);
    }

}
