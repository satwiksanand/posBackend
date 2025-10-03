package com.github.satwiksanand.posSystem.payload.dto;

import com.github.satwiksanand.posSystem.domain.UserRole;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserDto {
    private Long id;
    private String fullname;
    private String email;
    private String password;
    private String phone;
    private UserRole role;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime lastLogin;
}
