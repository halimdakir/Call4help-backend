package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dtos.ProfileDTO;
import com.solidbeans.call4help.entities.Profile;

public interface ProfileService {
    Profile updateUserInfos(String userId, ProfileDTO infos);
    void saveUserInfos(String userId);
    Profile findProfileByUserId(String userId);
    ProfileDTO findProfileDTO(String userId);
}
