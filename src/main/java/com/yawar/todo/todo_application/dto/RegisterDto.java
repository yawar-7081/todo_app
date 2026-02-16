package com.yawar.todo.todo_application.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterDto {
    private Long id;
    private String fullName;
    private String email;
    private String password;
}
