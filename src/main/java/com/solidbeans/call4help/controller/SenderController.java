package com.solidbeans.call4help.controller;

import com.solidbeans.call4help.notification.AmazonSNSService;
import com.solidbeans.call4help.service.AlertService;
import com.solidbeans.call4help.service.EndpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/sender", produces = MediaType.APPLICATION_JSON_VALUE)
public class SenderController {

    @Autowired
    private AlertService alertService;

    @Autowired
    private EndpointService endpointService;

    @Autowired
    private AmazonSNSService amazonSNSService;

    //TODO Language of the MESSAGE depending on the language used on the phone

    @GetMapping("userId/{userId}")
    public ResponseEntity<?> sendHelpRequest(@PathVariable String userId) {

        alertService.registerHelpAlert(userId);

        //Publish message to the nearest users
        amazonSNSService.publishMessage(endpointService.getEndpointsByPosition(userId), "I need help!");

        return ResponseEntity.ok("Message has successfully sent!");
    }
}
