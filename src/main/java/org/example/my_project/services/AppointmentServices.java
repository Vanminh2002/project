package org.example.my_project.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.my_project.dto.request.AppointmentRequest;
import org.example.my_project.dto.response.AppointmentResponse;
import org.example.my_project.entities.Appointment;
import org.example.my_project.entities.Doctor;
import org.example.my_project.entities.Patients;
import org.example.my_project.mapper.AppointmentMapper;
import org.example.my_project.repository.AppointmentRepository;
import org.example.my_project.repository.DoctorRepository;
import org.example.my_project.repository.PatientsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppointmentServices {

    PatientsRepository patientsRepository;
    DoctorRepository doctorRepository;
    AppointmentMapper appointmentMapper;
    AppointmentRepository appointmentRepository;
    private final FileStorageService fileStorageService;

    @Transactional
    public AppointmentResponse createAppointment(AppointmentRequest request) {
        Patients patient = patientsRepository.findById(request.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));



        String imageUrl = null;
        if (request.getImageFile() != null) {
            try {
                // Lưu file và lấy URL
                imageUrl = fileStorageService.saveFile("appointment",request.getImageFile());
            } catch (IOException e) {
                throw new RuntimeException("Error uploading file", e);
            }
        }
        Appointment appointment = appointmentMapper.toEntity(request);
        appointment.setImageUrl(imageUrl);
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        Appointment savedAppointment = appointmentRepository.save(appointment);
        return appointmentMapper.toResponse(savedAppointment);
    }

    public AppointmentResponse getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        return appointmentMapper.toResponse(appointment);
    }

    @Transactional
    public AppointmentResponse updateAppointmentStatus(Long id, String status) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));




        appointment.setStatus(Appointment.Status.valueOf(status.toUpperCase()));
        Appointment updatedAppointment = appointmentRepository.save(appointment);

        return appointmentMapper.toResponse(updatedAppointment);
    }

    @Transactional
    public AppointmentResponse updateDoctor(Long appointmentId, Long newDoctorId) {
        // Lấy cuộc hẹn từ database
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        // Lấy bác sĩ mới từ database
        Doctor newDoctor = doctorRepository.findById(newDoctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        // Cập nhật thông tin bác sĩ
        appointment.setDoctor(newDoctor);

        // Lưu cuộc hẹn đã cập nhật
        Appointment updatedAppointment = appointmentRepository.save(appointment);

        // Trả về DTO phản hồi
        return appointmentMapper.toResponse(updatedAppointment);
    }
}
