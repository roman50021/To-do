package com.example.todo.controllers;


import com.example.todo.dto.LoginUserDto;
import com.example.todo.dto.UserDto;
import com.example.todo.models.User;
import com.example.todo.service.UserDetailsService;
import com.example.todo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class AuthController {

    private UserService userService;

    private UserDetailsService userDetailsService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("login")
    public String loginForm(Model model) {
        LoginUserDto loginUserDto = new LoginUserDto();
        model.addAttribute("loginUserDto", loginUserDto);
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginUserDto") LoginUserDto loginUserDto,
    RedirectAttributes redirectAttributes) {
        String email = loginUserDto.getEmail();
        String password = loginUserDto.getPassword();

        if (userDetailsService.authenticateByEmail(email, password)) {
            // Успешная авторизация
            redirectAttributes.addFlashAttribute("message", "Login successful");
            return "redirect:/home";
        } else {
            // Неправильный Email или пароль
            redirectAttributes.addFlashAttribute("error", "Invalid Email and Password");
            return "redirect:/login";
        }

    }

    // handler method to handle user registration request
    @GetMapping("register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle register user form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Завершаем текущий сеанс
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, SecurityContextHolder.getContext().getAuthentication());

        // Перенаправляем на страницу входа
        return "redirect:/login";
    }
}