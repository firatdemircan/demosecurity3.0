package org.demircan.demosecurity.service;

import org.demircan.demosecurity.models.dtos.UserResponseDTO;
import org.demircan.demosecurity.models.dtos.UserSaveDTO;

import java.util.List;

public interface UserService {
    Boolean saveUser(UserSaveDTO userSaveDTO);

    List<UserResponseDTO> getAll();
}
