package com.solidbeans.call4help.controllers;

import com.solidbeans.call4help.dtos.PositionDTO;
import com.solidbeans.call4help.notification.AmazonSNSService;
import com.solidbeans.call4help.service.AlertService;
import com.solidbeans.call4help.service.EndpointService;
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

    //TODO Language of the MESSAGE depending on the language used on the phone

    @PostMapping("create")
    public ResponseEntity<?> sendHelpRequest(PositionDTO positionDTO) {

        alertService.registerHelpAlert(positionDTO);

        //Publish message to the nearest users
        amazonSNSService.publishMessage(endpointService.getEndpointsByLocation(positionDTO.getUserId()), "I need help!");

        return ResponseEntity.ok("Message has successfully sent!");
    }

    @GetMapping("/alerts")
    public ResponseEntity<?> getALLAlerts(){
        return new ResponseEntity<>(alertService.getAllAlerts(), HttpStatus.OK);
    }
}
