package com.tiffino.userservice.service;

import com.tiffino.userservice.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO dto);
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(Long id, UserDTO dto);
    void deleteUser(Long id);
}
