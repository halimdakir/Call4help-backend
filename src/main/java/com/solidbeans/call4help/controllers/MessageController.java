package com.solidbeans.call4help.controllers;


import com.solidbeans.call4help.jms.JmsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auth/notification", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {

    private final JmsService jmsService;

    public MessageController(JmsService jmsService) {
        this.jmsService = jmsService;
    }

    @GetMapping("/message")
    public ResponseEntity<?> getMessageByHelperId(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-Auth-User") String userId){
        return new ResponseEntity<>(jmsService.messagesList(userId), HttpStatus.OK);
    }

}


