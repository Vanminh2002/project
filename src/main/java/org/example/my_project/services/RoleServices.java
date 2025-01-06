package org.example.my_project.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.my_project.dto.request.PermissionRequest;
import org.example.my_project.dto.request.RoleRequest;
import org.example.my_project.dto.response.PermissionResponse;
import org.example.my_project.dto.response.RoleResponse;
import org.example.my_project.entities.Permission;
import org.example.my_project.entities.Role;
import org.example.my_project.exception.AppException;
import org.example.my_project.exception.ErrorCode;
import org.example.my_project.mapper.PermissionMapper;
import org.example.my_project.mapper.RoleMapper;
import org.example.my_project.repository.PermissionRepository;
import org.example.my_project.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServices {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    public RoleResponse create(RoleRequest request) {
        Role role = roleMapper.toRole(request);
        var permission = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permission));


        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> getAll() {
        var permission = roleRepository.findAll();
        return permission.stream().map(roleMapper::toRoleResponse).toList();
    }

    public void delete(String name) {
       Role role = roleRepository.findById(name).orElseThrow(()->new AppException(ErrorCode.NOT_FOUND));
        roleRepository.deleteById(name);
    }
}
