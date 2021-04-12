package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dtos.UserDTO;
import com.solidbeans.call4help.entities.Users;
import com.solidbeans.call4help.exception.NotFoundException;
import com.solidbeans.call4help.exception.RegistrationException;
import com.solidbeans.call4help.notification.AmazonSNSService;
import com.solidbeans.call4help.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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
    @Autowired
    private ProfileService profileService;
    @Autowired
    private AmazonSNSService amazonSNSService;

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


                userRepository.save(user);
                entityManager.detach(user);

            } else {                                       //Create new user

                user.setUserId(userDTO.getUserId());
                user.setAuthToken(userDTO.getAuthToken());

                user.setCreationDate(Instant.now().atZone(ZoneOffset.UTC));

                userRepository.save(user);
                entityManager.detach(user);

                //Create Profile
                profileService.saveUserInfos(user.getUserId());

                //Create Endpoint
                amazonSNSService.createAwsSnsEndpoint(user.getUserId());
            }

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
    public Optional<Users> findUserByPositionId(Long id) {
        return userRepository.findUsersByProfile_Position_Id(id);
    }

    @Override
    public Optional<UserDetails> findUserByToken(String authToken) {
        Optional<Users> user = userRepository.findUsersByAuthToken(authToken);
        if (user.isPresent()) {
            Users authenticatedUser = user.get();
            User springUser = new User(authenticatedUser.getUserId(), authenticatedUser.getAuthToken(), true, true, true, true,
                    AuthorityUtils.createAuthorityList("USER"));
            return Optional.of(springUser);
        }
        return Optional.empty();
    }

}
