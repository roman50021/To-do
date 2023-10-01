package com.example.todo.controllers;

import com.example.todo.dto.RegistrationUserDto;
import com.example.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;


@RequiredArgsConstructor
@org.springframework.stereotype.Controller
public class Controller {

    private final UserService userService;
    @GetMapping("/main")
    public String main(){
        return "main";
    }

    @GetMapping("/home")
    public String home(){ //secured
        return "home";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/info")
    public String userDate(Principal principal){
        return principal.getName();
    }


}
