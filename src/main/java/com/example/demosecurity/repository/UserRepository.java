package com.example.demosecurity.repository;

import com.example.demosecurity.models.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends BaseRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

}
