package com.backend.ir_system_api.services;

import com.backend.ir_system_api.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    boolean deleteUser(Long id);

    User updateUser(Long id, User user);
    User findByEmail(String email);
}