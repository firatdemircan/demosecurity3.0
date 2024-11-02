package com.example.demosecurity.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleEntity extends BaseEntity{

    private String rolename;

    @OneToMany(mappedBy = "roleEntity", orphanRemoval = true)
    private Set<UserEntity> userEntities = new LinkedHashSet<>();

    public RoleEntity(String rolename) {
        this.rolename = rolename;
    }
}
