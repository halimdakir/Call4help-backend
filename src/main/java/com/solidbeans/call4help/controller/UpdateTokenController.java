package com.solidbeans.call4help.controller;


import com.solidbeans.call4help.dto.TokenDTO;
import com.solidbeans.call4help.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/update", produces = MediaType.APPLICATION_JSON_VALUE)
public class UpdateTokenController {

    @Autowired
    private UserService userService;


    @PostMapping("/token")
    public ResponseEntity<?> updateToken(@RequestHeader("X-Auth-Token") String token, @RequestBody TokenDTO newToken){
        userService.updateToken(token, newToken.getToken());
        return new ResponseEntity<>("Token has successfully updated!", HttpStatus.OK);
    }
}
