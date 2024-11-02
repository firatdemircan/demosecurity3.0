package com.example.demosecurity.service.impl;

import com.example.demosecurity.models.dtos.UserResponseDTO;
import com.example.demosecurity.models.dtos.UserSaveDTO;
import com.example.demosecurity.models.entity.RoleEntity;
import com.example.demosecurity.models.entity.UserEntity;
import com.example.demosecurity.repository.RoleRepository;
import com.example.demosecurity.repository.UserRepository;
import com.example.demosecurity.service.UserService;
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
