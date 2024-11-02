package com.example.demosecurity.service;

import com.example.demosecurity.models.dtos.UserResponseDTO;
import com.example.demosecurity.models.dtos.UserSaveDTO;

import java.util.List;

public interface UserService {
    Boolean saveUser(UserSaveDTO userSaveDTO);

    List<UserResponseDTO> getAll();
}
