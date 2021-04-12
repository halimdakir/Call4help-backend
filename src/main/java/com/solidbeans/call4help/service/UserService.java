package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dtos.UserDTO;
import com.solidbeans.call4help.entities.Users;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;


public interface UserService {

    Users createNewUser(UserDTO user);

    Users updateToken(String token, String newToken);

    Users findUserById(Long id);

    Users findUserByUserId(String userId);

    List<Users> getAllUsers();

    void deleteUser(Long id);

    Optional<Users> findUserByPositionId(Long id);

    Optional<UserDetails> findUserByToken(String authToken);
}
