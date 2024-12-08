package org.example.my_project.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(999,"UNCATEGORIZED_EXCEPTION"),
    USER_EXISTS(1000, "username already exists"),
    NOT_FOUND(1001, "NOT_FOUND"),
    USERNAME_INVALID(1002, "Username is minimum 2 character and maximum 20 character"),
    PASSWORD_INVALID(1003, "Password must be at least 8 characters"),
    UPLOADING_EXCEPTION(1200, "Error uploading file"),


    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    int code;
    String message;
}
