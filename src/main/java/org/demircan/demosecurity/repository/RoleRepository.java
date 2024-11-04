package org.demircan.demosecurity.repository;

import org.demircan.demosecurity.models.entity.RoleEntity;

import java.util.Optional;

public interface RoleRepository extends BaseRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByRolename(String rolename);

}
