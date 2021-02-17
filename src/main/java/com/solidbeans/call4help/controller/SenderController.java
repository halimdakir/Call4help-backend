package com.solidbeans.call4help.controller;

import com.solidbeans.call4help.entity.Endpoints;
import com.solidbeans.call4help.notification.AmazonSNSService;
import com.solidbeans.call4help.service.AlertService;
import com.solidbeans.call4help.service.EndpointService;
import com.solidbeans.call4help.service.PositionService;
import com.solidbeans.call4help.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sender")
public class SenderController {

    @Autowired
    private AlertService alertService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private EndpointService endpointService;
    @Autowired
    private AmazonSNSService amazonSNSService;



    @GetMapping("userId/{userId}")
    public ResponseEntity<?> sendHelpRequest(@PathVariable String userId){

        alertService.registerHelpAlert(userId);

        //Get user nearest users
        List<Endpoints> endpointsList = endpointService.getEndpointsByPosition(userId);


        //Publish message to the nearest users
        //TODO Language of the MESSAGE depending on the language used on the phone

        amazonSNSService.publishMessage(endpointsList, "I need help!");

        return new ResponseEntity<>("Message has successfully sent!", HttpStatus.OK);
    }
}
