package com.example.todo.repos;

import com.example.todo.models.Role;
import com.example.todo.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    @Override
    Optional<User> findById(Long aLong);
    Optional<User> findByUsername(String username);
}
