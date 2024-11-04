package com.example.demosecurity.controller;

import com.example.demosecurity.models.entity.RoleEntity;
import com.example.demosecurity.models.entity.UserEntity;
import com.example.demosecurity.repository.RoleRepository;
import com.example.demosecurity.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("test")
public class TestController {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;


    @GetMapping("xx")
    public String testSecurity(){
        return "Çalışıyor";
    }


    @PostConstruct
    public void xx(){
        RoleEntity roleForSave = new RoleEntity();

        Optional<RoleEntity> roleEntityById = roleRepository.findByRolename("admin");
        if(roleEntityById.isEmpty()){
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setRolename("admin");
            roleForSave = roleRepository.save(roleEntity);
        }else{
            roleForSave = roleEntityById.get();
        }

        Optional<UserEntity> userEntityById = userRepository.findByUsername("firat.demircan");
        if(userEntityById.isEmpty()){
            UserEntity userEntity = new UserEntity("firat.demircan","123456",roleForSave);
            userRepository.save(userEntity);
        }

    }


}
