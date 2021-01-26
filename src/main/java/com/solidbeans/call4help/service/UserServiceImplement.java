package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dto.UserDTO;
import com.solidbeans.call4help.entity.User;
import com.solidbeans.call4help.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
public class UserServiceImplement implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDTO createNewUser(UserDTO userDTO) {


            var userFound = userRepository.findUserByUserId(userDTO.getUserId());
            User user = new User();

            if (userFound !=null){                        //User already exist
                user = userFound;
                user.setAuthToken(userDTO.getAuthToken());

            }else {                                       //Create new user
                user.setUserId(userDTO.getUserId());
                user.setAuthToken(userDTO.getAuthToken());

                user.setCreationDate(Instant.now().atZone(ZoneOffset.UTC));
                user.setLatestCall4HelpDate(null);
            }

            userRepository.save(user);

        return userDTO;
    }

    @Override
    public UserDTO updateToken(String token, String newToken) {
        return null;
    }
}
