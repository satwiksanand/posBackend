package com.github.satwiksanand.posSystem.service;

import com.github.satwiksanand.posSystem.exception.UserException;
import com.github.satwiksanand.posSystem.payload.dto.UserDto;
import com.github.satwiksanand.posSystem.payload.response.AuthResponse;

public interface AuthService {
    AuthResponse signup(UserDto user) throws UserException;
    AuthResponse login(UserDto user) throws UserException;
}
