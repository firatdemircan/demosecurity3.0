package com.example.demosecurity.controller;

import com.example.demosecurity.models.entity.RoleEntity;
import com.example.demosecurity.models.entity.UserEntity;
import com.example.demosecurity.repository.RoleRepository;
import com.example.demosecurity.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TestController {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;


    @PostConstruct
    public void xx(){
        RoleEntity roleForSave = new RoleEntity();

        Optional<RoleEntity> roleEntityById = roleRepository.findByRolename("admin");
        if(!roleEntityById.isPresent()){
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setRolename("admin");
            roleForSave = roleRepository.save(roleEntity);
        }else{
            roleForSave = roleEntityById.get();
        }

        Optional<UserEntity> userEntityById = userRepository.findByUsername("firat.demircan");
        if(!userEntityById.isPresent()){
            UserEntity userEntity = new UserEntity("firat.demircan","123456",roleForSave);
            userRepository.save(userEntity);
        }

    }


}
