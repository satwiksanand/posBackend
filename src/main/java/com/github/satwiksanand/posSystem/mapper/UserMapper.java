package com.github.satwiksanand.posSystem.mapper;

import com.github.satwiksanand.posSystem.models.User;
import com.github.satwiksanand.posSystem.payload.dto.UserDto;

public class UserMapper {
    public static UserDto toDTO(User savedUser) {
        return UserDto.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .phone(savedUser.getPhone())
                .role(savedUser.getRole())
                .fullname(savedUser.getFullname())
                .lastLogin(savedUser.getLastLogin())
                .modifiedAt(savedUser.getModifiedAt())
                .createdAt(savedUser.getCreatedAt())
                .build();
    }
}
