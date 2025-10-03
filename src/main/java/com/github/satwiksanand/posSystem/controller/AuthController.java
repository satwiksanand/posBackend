package com.github.satwiksanand.posSystem.controller;

import com.github.satwiksanand.posSystem.exception.UserException;
import com.github.satwiksanand.posSystem.payload.dto.UserDto;
import com.github.satwiksanand.posSystem.payload.response.AuthResponse;
import com.github.satwiksanand.posSystem.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> singUpHandler(@RequestBody UserDto userDto) throws UserException {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signup(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> logInHandler(@RequestBody UserDto userDto) throws UserException {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.login(userDto));
    }
}
