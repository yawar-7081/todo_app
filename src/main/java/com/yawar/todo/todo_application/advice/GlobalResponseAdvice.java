package com.yawar.todo.todo_application.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Arrays;

@RestControllerAdvice
public class GlobalResponseAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Nullable
    @Override
    public Object beforeBodyWrite(
            @Nullable Object body, MethodParameter returnType, MediaType selectedContentType,
            Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response
    ) {

        if (body instanceof ApiResponse<?>) {
            return body;
        }

        if (body instanceof Exception) {
            return ApiResponse.error(
                    ((Exception) body).getMessage(),
                    Arrays.stream(((Exception) body).getStackTrace()).map(StackTraceElement::toString).toList(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        return ApiResponse.success("SUCCESS",body,HttpStatus.OK);
    }
}
