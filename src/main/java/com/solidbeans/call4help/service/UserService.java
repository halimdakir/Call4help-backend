package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dto.UserDTO;

import java.util.Optional;

public interface UserService {
    UserDTO createNewUser(UserDTO user);
    UserDTO updateToken(String token, String newToken);
}
