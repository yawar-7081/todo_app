package com.yawar.todo.todo_application.advice;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ApiResponse<T> {

    private boolean success;
    private int status;
    private String message;
    private T data;
    private List<String> errors;
    private String timestamp;

    private ApiResponse(boolean success, int status, String message, T data, List<String> errors) {
        this.success = success;
        this.status = status;
        this.message = message;
        this.data = data;
        this.errors = errors;
        this.timestamp = java.time.OffsetDateTime.now().toString();
    }

    // ✅ Success factory
    public static <T> ApiResponse<T> success(int status, String message, T data) {
        return new ApiResponse<>(true, status, message, data, null);
    }

    // ❌ Error factory
    public static <T> ApiResponse<T> error(int status, String message, List<String> errors) {
        return new ApiResponse<>(false, status, message, null, errors);
    }
}




// getters
