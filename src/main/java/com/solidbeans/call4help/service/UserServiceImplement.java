package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dto.UserDTO;
import com.solidbeans.call4help.entity.Users;
import com.solidbeans.call4help.exception.NotFoundException;
import com.solidbeans.call4help.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneOffset;

@Service
public class UserServiceImplement implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public Users createNewUser(UserDTO userDTO) {


            var userFound = userRepository.findUserByUserId(userDTO.getUserId());
            Users user = new Users();

            if (userFound !=null){                        //User already exist
                user = userFound;
                user.setAuthToken(userDTO.getAuthToken());

            }else {                                       //Create new user
                user.setUserId(userDTO.getUserId());
                user.setAuthToken(userDTO.getAuthToken());

                user.setCreationDate(Instant.now().atZone(ZoneOffset.UTC));
                user.setLatestCall4HelpDate(null);
            }

            return userRepository.save(user);
    }

    @Override
    public UserDTO updateToken(String token, String newToken) {
        return null;
    }

    @Override
    public Users findUserById(Long id) {
        Users user = userRepository.findUsersById(id);
        if (user != null){
            return user;
        }else {
            throw new NotFoundException("User with id :"+id+" is not found");
        }
    }
}
