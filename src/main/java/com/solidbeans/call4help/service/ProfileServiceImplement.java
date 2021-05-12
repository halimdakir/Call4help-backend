package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dtos.ProfileDTO;
import com.solidbeans.call4help.dtos.ProfileModel;
import com.solidbeans.call4help.entities.Profile;
import com.solidbeans.call4help.exception.NotFoundException;
import com.solidbeans.call4help.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;
import java.util.List;

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
                    profile.setAnonymous(infos.isAnonymous());
                    profile.setFullName(infos.getFullName());
                    profile.setEmail(infos.getEmail());
                    profile.setPhoneNumber(infos.getPhoneNumber());
                    profile.setAddress(infos.getAddress());
                    profile.setPostCode(infos.getPostCode());
                    profile.setOrt(infos.getOrt());
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

                Profile newProfile = new Profile(false, null, null,null,null,null, null, null, user);
                profileRepository.save(newProfile);

            }
        }
    }

    @Override
    public Profile findProfileByUserId(String userId) {
        var profile = profileRepository.findProfileByUsers_UserId(userId);

        if (profile.isPresent()){
            return profile.get();
        }else {
            throw  new NotFoundException("Profile with user id :"+userId+" not found!");
        }
    }

    @Override
    public ProfileDTO findProfileDTO(String userId) {
        var user = userService.findUserByUserId(userId);
        if (user != null){
            var profile = profileRepository.returnCustomisedProfile(user.getId());
            if (profile != null){
                return profile;
            }else {
                throw  new NotFoundException("Profile with user id :"+userId+" not found!");
            }
        }else {
            throw  new NotFoundException("User with user id :"+userId+" not found!");
        }
    }
}
