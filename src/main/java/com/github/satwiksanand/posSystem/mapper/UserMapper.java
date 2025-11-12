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
                .password(savedUser.getPassword())
                .branchId(savedUser.getBranch() != null ? savedUser.getBranch().getId() : null)
                .storeId(savedUser.getStore() != null ? savedUser.getStore().getId() : null)
                .fullname(savedUser.getFullname())
                .lastLogin(savedUser.getLastLogin())
                .modifiedAt(savedUser.getModifiedAt())
                .createdAt(savedUser.getCreatedAt())
                .build();
    }

    public static User toEntity(UserDto userDto){
        return User.builder()
                .id(userDto.getId())
                .role(userDto.getRole())
                .phone(userDto.getPhone())
                .fullname(userDto.getFullname())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .createdAt(userDto.getCreatedAt())
                .lastLogin(userDto.getLastLogin())
                .modifiedAt(userDto.getModifiedAt())
                .build();
    }
}
