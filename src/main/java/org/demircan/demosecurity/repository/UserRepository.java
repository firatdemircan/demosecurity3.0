package org.demircan.demosecurity.repository;

import org.demircan.demosecurity.models.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends BaseRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

}
