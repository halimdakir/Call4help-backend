package com.solidbeans.call4help.controllers;

import com.solidbeans.call4help.dtos.UserDTO;
import com.solidbeans.call4help.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // put the user register in this class instead of a separate class.
    @PostMapping(value = "/create")
    public ResponseEntity<?> registerNewUser(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createNewUser(userDTO));
    }


}
