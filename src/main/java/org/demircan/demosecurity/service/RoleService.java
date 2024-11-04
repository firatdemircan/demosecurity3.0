package org.demircan.demosecurity.service;

import org.demircan.demosecurity.models.dtos.RoleResponseDTO;
import org.demircan.demosecurity.models.dtos.RoleSaveDTO;

import java.util.List;

public interface RoleService {
    Boolean saveRole(RoleSaveDTO roleSaveDTO);

    List<RoleResponseDTO> getAll();
}
