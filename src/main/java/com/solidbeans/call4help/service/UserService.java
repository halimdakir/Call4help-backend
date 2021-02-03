package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dto.UserDTO;
import com.solidbeans.call4help.entity.Users;


public interface UserService {
    Users createNewUser(UserDTO user);
    UserDTO updateToken(String token, String newToken);
    Users findUserById(Long id);
}
