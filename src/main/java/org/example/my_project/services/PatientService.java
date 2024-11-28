package org.example.my_project.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.my_project.dto.request.PatientRequest;
import org.example.my_project.dto.response.PatientResponse;
import org.example.my_project.entities.Doctor;
import org.example.my_project.entities.Patients;
import org.example.my_project.mapper.PatientMapper;
import org.example.my_project.repository.DoctorRepository;
import org.example.my_project.repository.PatientsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PatientService {


    PatientMapper patientMapper;
    DoctorRepository doctorRepository;
    PatientsRepository patientsRepository;

    public PatientResponse createPatient(PatientRequest dto) {
        Patients patient = patientMapper.toEntity(dto);
        if (dto.getDoctor_id() != null) {
            Doctor doctor = doctorRepository.findById(dto.getDoctor_id())
                    .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + dto.getDoctor_id()));
            patient.setDoctor(doctor);
        }
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

        Patients updatePatient = patientsRepository.save(patients);

        return patientMapper.toDto(updatePatient);

    }
    public void deletePatient(Long id) {
        Patients patients = patientsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));

        patientsRepository.deleteById(id);
    }
}
