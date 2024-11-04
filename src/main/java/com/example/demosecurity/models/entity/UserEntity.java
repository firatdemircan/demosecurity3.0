package com.example.demosecurity.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(uniqueConstraints = {@UniqueConstraint(name = "UniqueAyAndYil", columnNames = {"username"})})
public class UserEntity extends BaseEntity{

    private String username;
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_entity_id")
    private RoleEntity roleEntity;

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
