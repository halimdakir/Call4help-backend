package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dtos.UserDTO;
import com.solidbeans.call4help.entities.Users;
import com.solidbeans.call4help.exception.NotFoundException;
import com.solidbeans.call4help.exception.RegistrationException;
import com.solidbeans.call4help.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplement implements UserService{

    @Autowired
    UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Users createNewUser(UserDTO userDTO) {
        Users user = new Users();

        if (userDTO.getUserId() == null || userDTO.getUserId().isBlank() && userDTO.getAuthToken().isEmpty()){

            throw new RegistrationException("Couldn't Register.");

        }else {

            var userFound = userRepository.findUserByUserId(userDTO.getUserId());


            if (userFound != null) {                        //User already exist
                user = userFound;
                user.setAuthToken(userDTO.getAuthToken());

            } else {                                       //Create new user
                user.setUserId(userDTO.getUserId());
                user.setAuthToken(userDTO.getAuthToken());

                user.setCreationDate(Instant.now().atZone(ZoneOffset.UTC));
            }

            userRepository.save(user);
            entityManager.detach(user);

            return user;
        }
    }

    @Override
    public Users updateToken(String token, String newToken) {
        return userRepository.findUsersByAuthToken(token)
                .map(user -> {
                    user.setAuthToken(newToken);
                    user.setTokenUpdateDate(ZonedDateTime.now());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new NotFoundException("User not found with token :" + token)
                );

    }

    @Override
    public Users findUserByUserId(String userId) {
        Users user = userRepository.findUserByUserId(userId);
        if (user != null){
            return user;
        }else {
            throw new NotFoundException("User with id :"+userId+" is not found");
        }
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

    @Override
    public List<Users> getAllUsers() {
        return (List<Users>) userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        var user = userRepository.findUsersById(id);
        if (user!=null){
            userRepository.deleteById(id);
        }else {
            throw new NotFoundException("User with id :"+id+" is not found");
        }
    }

    @Override
    public Optional<Users> getUserByLocationId(Long id) {
        return userRepository.findUsersByLocationId(id);
    }

    @Override
    public Optional<Users> findUserByPositionId(Long id) {
        return userRepository.findUsersByLocation_Position_Id(id);
    }

}
