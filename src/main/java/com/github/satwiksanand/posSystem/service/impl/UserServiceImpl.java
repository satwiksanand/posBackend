package com.github.satwiksanand.posSystem.service.impl;

import com.github.satwiksanand.posSystem.configurations.JwtProvider;
import com.github.satwiksanand.posSystem.exception.UserException;
import com.github.satwiksanand.posSystem.models.User;
import com.github.satwiksanand.posSystem.repository.UserRepository;
import com.github.satwiksanand.posSystem.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public UserServiceImpl(UserRepository userRepository, JwtProvider jwtProvider){
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public User getUserFromJwtToken(String jwt) throws UserException{
        String email = jwtProvider.getEmailFromToken(jwt);
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UserException("Invalid token");// can happen when the user is deleted and someone tries logging in with an old token.
        }
        return user;
    }

    @Override
    public User getCurrentUser() throws UserException{
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UserException("user not found!");
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws UserException {
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UserException("user not found!");
        }
        return user;
    }

    @Override
    public User getUserById(Long id) throws UserException {
        return userRepository.findById(id).orElseThrow(
                () ->new UserException("user not found!")
        );
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
