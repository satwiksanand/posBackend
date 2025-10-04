package com.github.satwiksanand.posSystem.service;

import com.github.satwiksanand.posSystem.exception.UserException;
import com.github.satwiksanand.posSystem.models.User;

import java.util.List;

public interface UserService {
    User getUserFromJwtToken(String jwt) throws UserException;
    User getCurrentUser() throws UserException;
    User getUserByEmail(String email) throws UserException;
    User getUserById(Long id) throws UserException;
    List<User> getAllUsers();
}
