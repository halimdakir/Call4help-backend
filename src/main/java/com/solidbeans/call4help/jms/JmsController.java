package com.solidbeans.call4help.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class JmsController {

    @Autowired
    private JmsService jmsService;

/*
    @PostMapping("/publishMessage")
    public ResponseEntity<?> publishMessage(@RequestBody MessageObject messageObject) {
      jmsService.publishMessage(messageObject);
      return new ResponseEntity<>("Message has sent!", HttpStatus.OK);
    }

 */
}
