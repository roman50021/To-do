package com.example.todo.controllers;


import com.example.todo.dto.RegistrationUserDto;
import com.example.todo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;


@RequiredArgsConstructor
@Controller
public class AuthController {
    private final AuthService authService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(){
        return "registration";
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        System.out.println(registrationUserDto.getClass().getName());
        return authService.createNewUser(registrationUserDto);
    }

}