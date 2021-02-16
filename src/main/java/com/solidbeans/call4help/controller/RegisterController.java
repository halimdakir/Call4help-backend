package com.solidbeans.call4help.controller;

import com.solidbeans.call4help.dto.UserDTO;
import com.solidbeans.call4help.entity.Users;
import com.solidbeans.call4help.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<Users> registerNewUser(@Valid @RequestBody UserDTO userDTO){

            var user = userService.createNewUser(userDTO);
            return new ResponseEntity<>(user, HttpStatus.OK);

    }

}
