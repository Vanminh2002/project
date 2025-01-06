package org.example.my_project.mapper;

import org.example.my_project.dto.request.RoleRequest;
import org.example.my_project.dto.response.RoleResponse;
import org.example.my_project.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);


    RoleResponse toRoleResponse(Role role);

//    void updateRole(@MappingTarget Role role, RoleRequest request);
}
