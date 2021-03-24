package com.solidbeans.call4help.controllers;

import com.solidbeans.call4help.dtos.UserDTO;
import com.solidbeans.call4help.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    // put the user register in this class instead of a separate class.
    @PostMapping(value = "/create")
    public ResponseEntity<?> registerNewUser(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createNewUser(userDTO));
    }

    // use parameters when searching.
    // for example localhost:8080/api/v1/users should automatically return all users
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
