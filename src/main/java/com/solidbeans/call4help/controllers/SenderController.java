package com.solidbeans.call4help.controllers;

import com.solidbeans.call4help.dtos.PositionDTO;
import com.solidbeans.call4help.jms.JmsService;
import com.solidbeans.call4help.notification.AmazonSNSService;
import com.solidbeans.call4help.service.AlertService;
import com.solidbeans.call4help.service.EndpointService;
import com.solidbeans.call4help.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/sender", produces = MediaType.APPLICATION_JSON_VALUE)
public class SenderController {

    @Autowired
    private AlertService alertService;

    @Autowired
    private EndpointService endpointService;

    @Autowired
    private AmazonSNSService amazonSNSService;

    @Autowired
    private JmsService jmsService;

    @Autowired
    private PositionService positionService;



    //TODO Language of the MESSAGE depending on the language used on the phone
    //TODO Delete published messages after 24 hours

    @PostMapping("create")
    public ResponseEntity<?> sendHelpRequest(@RequestBody PositionDTO positionDTO) {

        var alert = alertService.registerHelpAlert(positionDTO);

        //Push notification
        amazonSNSService.publishMessage(endpointService.getEndpointsByLocation(positionDTO.getUserId()), "Someone needs help!");

        //Publish message
        jmsService.publishMessage(positionService.getNearestUsers(alert.getId()));


        return new ResponseEntity<>(HttpStatus.OK);
    }

    //TODO THIS IS ONLY FOR TEST
    @GetMapping("/alerts")
    public ResponseEntity<?> getALLAlerts(){
        return new ResponseEntity<>(alertService.getAllAlerts(), HttpStatus.OK);
    }

}
