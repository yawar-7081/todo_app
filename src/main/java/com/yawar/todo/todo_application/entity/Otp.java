package com.yawar.todo.todo_application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "otp_tx")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "otp",nullable = false,updatable = false)
    private String otp;

    @Column(nullable = false,updatable = false,name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "expiration_at",nullable = false,updatable = false)
    private LocalDateTime expirationAt;

    @Column(name = "username",nullable = false,updatable = false)
    private String username;
}
