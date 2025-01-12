package org.example.my_project.mapper;

import org.example.my_project.dto.request.UserRequest;
import org.example.my_project.dto.response.UserResponse;
import org.example.my_project.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roles", ignore = true)
    User toUser(UserRequest user);

    @Mapping(source = "imageUrl", target = "imageUrl")
//    @Mapping(source = "password", target = "password")
    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserRequest userRequest);
}
