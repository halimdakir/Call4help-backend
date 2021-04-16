package com.solidbeans.call4help.controllers;

import com.solidbeans.call4help.dtos.TokenDTO;
import com.solidbeans.call4help.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/auth/token", produces = MediaType.APPLICATION_JSON_VALUE)
public class UpdateTokenController {

    private final UserService userService;

    public UpdateTokenController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateToken(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-Auth-User") String userId, @RequestBody TokenDTO newToken) {
        return ResponseEntity.ok(userService.updateToken(token, newToken.getToken()));
    }
}
