package com.solidbeans.call4help.controller;

import com.solidbeans.call4help.entity.Users;
import com.solidbeans.call4help.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<Users> getAllUsers(){
        return userService.getAllUsers();
    }


    @DeleteMapping(value = "/id/{id}", produces = "application/json")
    public ResponseEntity<?> registerNewUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("Successfully deleted!", HttpStatus.OK);

    }

}
