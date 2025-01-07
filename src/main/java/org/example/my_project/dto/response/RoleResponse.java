package org.example.my_project.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.example.my_project.entities.Permission;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleResponse {
    String name;
    String description;
    Set<PermissionResponse> permissions;

}
