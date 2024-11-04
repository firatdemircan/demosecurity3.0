package org.demircan.demosecurity.service.impl;

import org.demircan.demosecurity.models.dtos.RoleResponseDTO;
import org.demircan.demosecurity.models.dtos.RoleSaveDTO;
import org.demircan.demosecurity.models.entity.RoleEntity;
import org.demircan.demosecurity.repository.RoleRepository;
import org.demircan.demosecurity.service.RoleService;
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
