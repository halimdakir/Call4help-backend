package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dtos.UserDTO;
import com.solidbeans.call4help.entities.Users;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Optional;


public interface UserService {

    Users createNewUser(UserDTO user);

    Users updateToken(String token, String newToken);

    Users findUserByUserId(String userId);

    Optional<Users> findUserByPositionId(Long id);

    Optional<UserDetails> findUserByToken(String authToken);
}
