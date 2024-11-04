package org.demircan.demosecurity.models.dtos;

public record UserSaveDTO(String username, String password,Long roleId) {
}
