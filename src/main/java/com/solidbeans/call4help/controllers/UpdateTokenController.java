package com.solidbeans.call4help.controllers;

import com.solidbeans.call4help.dtos.TokenDTO;
import com.solidbeans.call4help.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/update", produces = MediaType.APPLICATION_JSON_VALUE)
public class UpdateTokenController {

    @Autowired
    private UserService userService;

    @PostMapping("/token")
    public ResponseEntity<?> updateToken(@RequestHeader("X-Auth-Token") String token, @RequestBody TokenDTO newToken) {
        return ResponseEntity.ok(userService.updateToken(token, newToken.getToken()));
    }
}
