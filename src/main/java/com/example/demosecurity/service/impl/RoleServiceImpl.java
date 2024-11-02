package com.example.demosecurity.service.impl;

import com.example.demosecurity.models.dtos.RoleResponseDTO;
import com.example.demosecurity.models.dtos.RoleSaveDTO;
import com.example.demosecurity.models.entity.RoleEntity;
import com.example.demosecurity.repository.RoleRepository;
import com.example.demosecurity.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;


    @Override
    public Boolean saveRole(RoleSaveDTO roleSaveDTO) {
        RoleEntity roleEntity = new RoleEntity(roleSaveDTO.rolename());

       roleRepository.save(roleEntity);

        return true;
    }

    @Override
    public List<RoleResponseDTO> getAll() {
       return roleRepository.findAll().stream().map(a->new RoleResponseDTO(a.getRolename())).toList();
    }
}
