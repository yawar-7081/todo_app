package com.yawar.todo.todo_application.controller;

import com.yawar.todo.todo_application.advice.ApiResponse;
import com.yawar.todo.todo_application.dto.LoginDto;
import com.yawar.todo.todo_application.dto.RegisterDto;
import com.yawar.todo.todo_application.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterDto registerDto){
        log.info("Register Controller Started !");
        RegisterDto result = userService.register(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.success(HttpStatus.CREATED.value(),"User Registered Successfully",result)
        );
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginDto loginDto){
        String token = userService.login(loginDto);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(HttpStatus.OK.value(),"Login Successfully",token));
    }

}
