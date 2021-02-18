package com.solidbeans.call4help.controller;

import com.solidbeans.call4help.entity.Users;
import com.solidbeans.call4help.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/v1/users", produces = "application/json")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers(){
        var users =  userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity<?> registerNewUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("Successfully deleted!", HttpStatus.OK);

    }

}
