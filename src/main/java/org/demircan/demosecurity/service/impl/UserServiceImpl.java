package org.demircan.demosecurity.service.impl;

import org.demircan.demosecurity.models.dtos.UserResponseDTO;
import org.demircan.demosecurity.models.dtos.UserSaveDTO;
import org.demircan.demosecurity.models.entity.RoleEntity;
import org.demircan.demosecurity.models.entity.UserEntity;
import org.demircan.demosecurity.repository.RoleRepository;
import org.demircan.demosecurity.repository.UserRepository;
import org.demircan.demosecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public Boolean saveUser(UserSaveDTO userSaveDTO) {
        RoleEntity roleEntity = roleRepository.getReferenceById(userSaveDTO.roleId());
        UserEntity userEntity = new UserEntity(userSaveDTO.username(),userSaveDTO.password(),roleEntity);
        userRepository.save(userEntity);
        return true;
    }

    @Override
    public List<UserResponseDTO> getAll() {
        return userRepository.findAll().stream().map(a->new UserResponseDTO(a.getUsername(),a.getRoleEntity().getRolename())).toList();
    }
}
