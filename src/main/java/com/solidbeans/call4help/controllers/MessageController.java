package com.solidbeans.call4help.controllers;


import com.solidbeans.call4help.jms.JmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/auth/notification", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {

    @Autowired
    private JmsService jmsService;


    @GetMapping("/message")
    public ResponseEntity<?> getMessageByHelperId(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-Auth-User") String userId){
        return new ResponseEntity<>(jmsService.messagesList(userId), HttpStatus.OK);
    }

}


