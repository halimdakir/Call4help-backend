package com.solidbeans.call4help.controller;

import com.solidbeans.call4help.entity.Users;
import com.solidbeans.call4help.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
