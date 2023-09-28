package com.example.todo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;


@RequiredArgsConstructor
@org.springframework.stereotype.Controller
public class Controller {
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
