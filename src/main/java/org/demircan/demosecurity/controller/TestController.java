package org.demircan.demosecurity.controller;

import jakarta.annotation.security.PermitAll;
import org.demircan.demosecurity.models.entity.RoleEntity;
import org.demircan.demosecurity.models.entity.UserEntity;
import org.demircan.demosecurity.repository.RoleRepository;
import org.demircan.demosecurity.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.demircan.demosecurity.repository.customrepo.UserRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("test")
@PermitAll
public class TestController {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;


    @GetMapping("xx")
    public String testSecurity() {
        xx();
        return "Çalışıyor";
    }


    @PostConstruct
    public void xx() {
        new RoleEntity();
        RoleEntity roleForSave;

        Optional<RoleEntity> roleEntityById = roleRepository.findByRolename("ADMIN");
        if (roleEntityById.isEmpty()) {
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setRolename("admin");
            roleForSave = roleRepository.save(roleEntity);
        } else {
            roleForSave = roleEntityById.get();
        }

        Optional<UserEntity> userEntityById = userRepository.findByUsername("firat.demircan");
        if (userEntityById.isEmpty()) {
            UserEntity userEntity = new UserEntity("firat.demircan", "123456", roleForSave);
            userRepository.save(userEntity);
        }

    }


}
