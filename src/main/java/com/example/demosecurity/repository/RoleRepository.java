package com.example.demosecurity.repository;

import com.example.demosecurity.models.entity.RoleEntity;

import java.util.Optional;

public interface RoleRepository extends BaseRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByRolename(String rolename);

}
