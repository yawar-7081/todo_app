package com.yawar.todo.todo_application.service;

import com.yawar.todo.todo_application.dto.LoginDto;
import com.yawar.todo.todo_application.dto.RegisterDto;
import com.yawar.todo.todo_application.entity.User;
import com.yawar.todo.todo_application.repository.UserRepository;
import com.yawar.todo.todo_application.utils.CustomUserDetails;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public RegisterDto register(RegisterDto registerDto) {

        User user = new User();
        user.setUsername(registerDto.getEmail());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setFullName(registerDto.getFullName());


        User savedUser = userRepository.save(user);

        return modelMapper.map(savedUser,RegisterDto.class);
    }

    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        String token = jwtService.generateToken(user);

        return token;
    }
}
