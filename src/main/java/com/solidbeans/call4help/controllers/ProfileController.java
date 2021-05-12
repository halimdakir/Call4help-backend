package com.solidbeans.call4help.controllers;

import com.solidbeans.call4help.dtos.ProfileDTO;
import com.solidbeans.call4help.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {

   @Autowired
    private ProfileService profileService;


    @GetMapping(value = "/auth/profile/get")
    public ResponseEntity<?> getProfile(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-Auth-User") String userId) {
        return new ResponseEntity<>(profileService.findProfileDTO(userId), HttpStatus.OK);
    }


    @PutMapping( value = "/auth/profile/update")
    public ResponseEntity<?> updateProfile(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-Auth-User") String userId, @RequestBody ProfileDTO profile){
        var result = profileService.updateUserInfos(userId, profile);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //TODO ONLY FOR TEST
    @GetMapping(value="profile/all")
    public ResponseEntity<?> updateProfile(){
        return new ResponseEntity<>(profileService.allProfiles(), HttpStatus.OK);
    }
}
