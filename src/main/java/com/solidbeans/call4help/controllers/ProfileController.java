package com.solidbeans.call4help.controllers;

import com.solidbeans.call4help.dtos.ProfileDTO;
import com.solidbeans.call4help.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/auth/profile", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> updateProfile(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-Auth-User") String userId, @RequestBody ProfileDTO profile){
        var result = profileService.updateUserInfos(userId, profile);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
