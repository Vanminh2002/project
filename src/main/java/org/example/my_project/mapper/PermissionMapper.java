package org.example.my_project.mapper;

import org.example.my_project.dto.request.PermissionRequest;
import org.example.my_project.dto.response.PermissionResponse;
import org.example.my_project.entities.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission toPermission(PermissionRequest request);


    PermissionResponse toPermissionResponse(Permission permission);

    void updatePermission(@MappingTarget Permission permission, PermissionRequest request);
}
