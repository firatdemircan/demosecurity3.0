package com.example.demosecurity.service;

import com.example.demosecurity.models.dtos.RoleResponseDTO;
import com.example.demosecurity.models.dtos.RoleSaveDTO;

import java.util.List;

public interface RoleService {
    Boolean saveRole(RoleSaveDTO roleSaveDTO);

    List<RoleResponseDTO> getAll();
}
