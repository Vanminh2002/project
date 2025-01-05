package org.example.my_project.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.my_project.dto.request.AppointmentRequest;
import org.example.my_project.dto.request.PermissionRequest;
import org.example.my_project.dto.response.AppointmentResponse;
import org.example.my_project.dto.response.PermissionResponse;
import org.example.my_project.entities.Appointment;
import org.example.my_project.entities.Doctor;
import org.example.my_project.entities.Patients;
import org.example.my_project.entities.Permission;
import org.example.my_project.mapper.AppointmentMapper;
import org.example.my_project.mapper.PermissionMapper;
import org.example.my_project.repository.AppointmentRepository;
import org.example.my_project.repository.DoctorRepository;
import org.example.my_project.repository.PatientsRepository;
import org.example.my_project.repository.PermissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionServices {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAll() {
//        return permissionRepository.findAll().stream()
//                .map(permissionMapper::toUserResponse)
//                .collect(Collectors.toList());
        var permission = permissionRepository.findAll();
        return permission.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    public void delete(String name) {
        permissionRepository.deleteById(name);
    }
}
