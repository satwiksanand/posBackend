package com.github.satwiksanand.posSystem.payload.response;

import com.github.satwiksanand.posSystem.payload.dto.UserDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private String jwt;
    private String message;
    private UserDto userDto;
}
