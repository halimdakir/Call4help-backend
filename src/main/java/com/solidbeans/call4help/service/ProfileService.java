package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dtos.ProfileDTO;
import com.solidbeans.call4help.entities.Profile;

import java.util.Optional;

public interface ProfileService {
    Profile updateUserInfos(String userId, ProfileDTO infos);
    void saveUserInfos(String userId);
    Optional<Profile> findProfileByUserId(String userId);
}
