package org.example.my_project.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(999, "UNCATEGORIZED_EXCEPTION", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTS(1000, "username already exists", HttpStatus.BAD_REQUEST),
    NOT_FOUND(1001, "NOT_FOUND", HttpStatus.NOT_FOUND),
    USERNAME_INVALID(1002, "Username is minimum 2 character and maximum 20 character", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1003, "Password must be at least 4 characters", HttpStatus.BAD_REQUEST),
    UPLOADING_EXCEPTION(1200, "Error uploading file", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHENTICATED(3000, "UNAUTHENTICATED", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(3001, "You do not have permission", HttpStatus.FORBIDDEN),


    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    int code;
    String message;
    HttpStatusCode statusCode;
}
