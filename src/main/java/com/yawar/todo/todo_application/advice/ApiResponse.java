package com.yawar.todo.todo_application.advice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class ApiResponse<T> {

    private final String message;
    private final LocalDateTime timestamp;
    private final T data;
    private final List<String> errors;
    private final HttpStatus status;


    public static <T> ApiResponse<T> success(String message, T data, HttpStatus status){
        return new ApiResponse<>(message,LocalDateTime.now(),data,null,status);
    }

    public static <T> ApiResponse<T> error(String message, List<String> errors, HttpStatus status){
        return new ApiResponse<>(message,LocalDateTime.now(),null,errors,status);
    }
}
