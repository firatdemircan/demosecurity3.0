package com.example.demosecurity.controller;

import com.example.demosecurity.models.dtos.RoleResponseDTO;
import com.example.demosecurity.models.dtos.RoleSaveDTO;
import com.example.demosecurity.models.dtos.UserResponseDTO;
import com.example.demosecurity.models.dtos.UserSaveDTO;
import com.example.demosecurity.service.RoleService;
import com.example.demosecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("userrole")
@RequiredArgsConstructor
public class UserRoleController {

    private final UserService userService;
    private final RoleService roleService;

    @PostMapping("saveuser")
    public Boolean saveUser(@RequestBody UserSaveDTO userSaveDTO){
       return userService.saveUser(userSaveDTO);
    }

    @GetMapping("getAllUser")
    public List<UserResponseDTO> getAllUser(){
        return userService.getAll();
    }

    @PostMapping
    public Boolean saveRole(@RequestBody RoleSaveDTO roleSaveDTO){
       return roleService.saveRole(roleSaveDTO);
    }

    @GetMapping("getAllRoles")
    public List<RoleResponseDTO> getAllRoles(){
        return roleService.getAll();
    }

}
