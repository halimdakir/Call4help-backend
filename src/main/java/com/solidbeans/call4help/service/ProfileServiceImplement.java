package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dtos.ProfileDTO;
import com.solidbeans.call4help.entities.Profile;
import com.solidbeans.call4help.exception.NotFoundException;
import com.solidbeans.call4help.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
public class ProfileServiceImplement implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private UserService userService;



    @Override
    public Profile updateUserInfos(String userId, ProfileDTO infos) {

        return profileRepository.findProfileByUsers_UserId(userId)

                .map(profile -> {

                    profile.setFullName(infos.getFullName());
                    profile.setEmail(infos.getEmail());
                    profile.setPhoneNumber(infos.getPhoneNumber());
                    profile.setUpdateDate(ZonedDateTime.now());
                    return profileRepository.save(profile);


                })

                .orElseThrow(() -> new NotFoundException("Profile with user id :" + userId + " not found!")

                );
    }

    @Override
    public void saveUserInfos(String userId) {
        var profileIsExist = profileRepository.findProfileByUsers_UserId(userId);
        var user = userService.findUserByUserId(userId);

        if (profileIsExist.isEmpty()){
            if (user != null){

                Profile newProfile = new Profile(true, null, null,null,null, user);
                profileRepository.save(newProfile);

            }
        }
    }

    @Override
    public Optional<Profile> findProfileByUserId(String userId) {
        return profileRepository.findProfileByUsers_UserId(userId);
    }
}
