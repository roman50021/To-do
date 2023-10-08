package com.example.todo.service;

import com.example.todo.dto.UserDto;
import com.example.todo.models.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}
