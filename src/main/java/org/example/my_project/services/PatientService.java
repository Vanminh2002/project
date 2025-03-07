package org.example.my_project.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.my_project.dto.request.PatientRequest;
import org.example.my_project.dto.response.PatientResponse;
import org.example.my_project.entities.Doctor;
import org.example.my_project.entities.Patients;
import org.example.my_project.entities.User;
import org.example.my_project.exception.AppException;
import org.example.my_project.exception.ErrorCode;
import org.example.my_project.mapper.PatientMapper;
import org.example.my_project.repository.DoctorRepository;
import org.example.my_project.repository.PatientsRepository;
import org.example.my_project.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PatientService {


    PatientMapper patientMapper;
    DoctorRepository doctorRepository;
    PatientsRepository patientsRepository;
    private final FileStorageService fileStorageService;
    PasswordEncoder passwordEncoder;
    UserRepository userRepository;

    public PatientResponse createPatient(PatientRequest dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new AppException(ErrorCode.USER_EXISTS);
        }
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword())); // Mã hóa mật khẩu
//        user.setRoles(Set.of(new Role("ROLE_DOCTOR"))); // Gán vai trò ROLE_DOCTOR
//        userRepository.save(user);
        String imageUrl = null;
        if (dto.getImageFile() != null) {
            try {
                // Lưu file và lấy URL
                imageUrl = fileStorageService.saveFile("patient", dto.getImageFile());
            } catch (IOException e) {
                throw new RuntimeException("Error uploading file", e);
            }
        }
        Patients patient = patientMapper.toEntity(dto);
        if (dto.getDoctor_id() != null) {
            Doctor doctor = doctorRepository.findById(dto.getDoctor_id())
                    .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + dto.getDoctor_id()));
            patient.setDoctor(doctor);
        }

        patient.setImageUrl(imageUrl);
        ;
        patient.setUser(user);
        userRepository.save(user);
        Patients savedPatient = patientsRepository.save(patient);

        return patientMapper.toDto(savedPatient);
    }

    public PatientResponse getPatientById(Long id) {
        Patients patient = patientsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
        return patientMapper.toDto(patient);
    }

    public List<PatientResponse> getAllPatient() {
        return patientsRepository.findAll().stream()
                .map(patientMapper::toDto)
                .collect(Collectors.toList());
    }

    public PatientResponse updatePatient(Long id, PatientRequest dto) {
        Patients patients = patientsRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient Not Found"));

        patientMapper.updateEntityFromDto(dto, patients);


        if (dto.getDoctor_id() != null) {
            Doctor doctor = doctorRepository.findById(dto.getDoctor_id())
                    .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + dto.getDoctor_id()));
            patients.setDoctor(doctor); // Gán bác sĩ cho bệnh nhân
        }
        String imageUrl = patients.getImageUrl();
        if (patients.getImageUrl() != null) {
            try {
                imageUrl = fileStorageService.replaceFile("patient", patients.getImageUrl(), dto.getImageFile());
            } catch (IOException e) {
                throw new RuntimeException("Error uploading file", e);
            }
        }
        patients.setImageUrl(imageUrl);

        Patients updatePatient = patientsRepository.save(patients);

        return patientMapper.toDto(updatePatient);

    }

    public void deletePatient(Long id) {
        Patients patients = patientsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));

        patientsRepository.delete(patients);
    }
   public PatientResponse  findByUserId(String userId) {
        Patients patient = patientsRepository.findByUserId(userId);
        return patientMapper.toDto(patient);


   }
}
