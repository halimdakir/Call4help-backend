package com.solidbeans.call4help.controllers;


import com.solidbeans.call4help.jms.JmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/message", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {

    @Autowired
    private JmsService jmsService;


    @GetMapping("helperId/{helper_id}")
    public ResponseEntity<?> getMessageByHelperId(@PathVariable Long helper_id){
        return new ResponseEntity<>(jmsService.messagesList(helper_id), HttpStatus.OK);
    }

}


