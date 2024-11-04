package org.demircan.demosecurity.controller;

import org.demircan.demosecurity.models.dtos.RoleResponseDTO;
import org.demircan.demosecurity.models.dtos.RoleSaveDTO;
import org.demircan.demosecurity.models.dtos.UserResponseDTO;
import org.demircan.demosecurity.models.dtos.UserSaveDTO;
import org.demircan.demosecurity.service.RoleService;
import org.demircan.demosecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("userrole")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
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
