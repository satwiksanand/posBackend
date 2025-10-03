package com.github.satwiksanand.posSystem.service.impl;

import com.github.satwiksanand.posSystem.configurations.JwtProvider;
import com.github.satwiksanand.posSystem.domain.UserRole;
import com.github.satwiksanand.posSystem.exception.UserException;
import com.github.satwiksanand.posSystem.mapper.UserMapper;
import com.github.satwiksanand.posSystem.models.User;
import com.github.satwiksanand.posSystem.payload.dto.UserDto;
import com.github.satwiksanand.posSystem.payload.response.AuthResponse;
import com.github.satwiksanand.posSystem.repository.UserRepository;
import com.github.satwiksanand.posSystem.service.AuthService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserImplementation customUserImplementation;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider, CustomUserImplementation customUserImplementation) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.customUserImplementation = customUserImplementation;
    }

    @Override
    public AuthResponse signup(UserDto user) throws UserException {
        if(userRepository.existsByEmail(user.getEmail())){
            throw new UserException("Email is already registered!");
        }
        if(user.getRole().equals(UserRole.ADMIN)){
            throw new UserException("role admin is not allowed!");
        }
        User newUser = User.builder()
                .fullname(user.getFullname())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole())
                .password(passwordEncoder.encode(user.getPassword()))
                .modifiedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .build();
        User savedUser = userRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        return AuthResponse.builder()
                .jwt(jwt)
                .message("User created successfully!")
                .userDto(UserMapper.toDTO(savedUser))
                .build();
    }

    @Override
    public AuthResponse login(UserDto userDto) throws UserException {
        Authentication authentication = authenticate(userDto.getEmail(), userDto.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.iterator().next().getAuthority();
        String jwt = jwtProvider.generateToken(authentication);
        User user = userRepository.findByEmail(userDto.getEmail());
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
        return AuthResponse.builder()
                .jwt(jwt)
                .message("logged in successfully!")
                .userDto(UserMapper.toDTO(user))
                .build();
    }

    private Authentication authenticate(String email, String password) throws UserException{
        UserDetails userDetails = customUserImplementation.loadUserByUsername(email);
        if(userDetails == null){
            throw new UserException("email id does not exist " + email);
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new UserException("wrong password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, userRepository, userDetails.getAuthorities());
    }
}
