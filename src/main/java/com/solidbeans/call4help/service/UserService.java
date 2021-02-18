package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dto.UserDTO;
import com.solidbeans.call4help.entity.Users;

import java.util.List;
import java.util.Optional;


public interface UserService {
    Users createNewUser(UserDTO user);
    Users updateToken(String token, String newToken);
    Users findUserById(Long id);
    Users findUserByUserId(String userId);
    List<Users> getAllUsers();
    void deleteUser(Long id);
    Optional<Users> getUserByPositionId(Long id);
}
