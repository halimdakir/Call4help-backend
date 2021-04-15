package com.solidbeans.call4help.controllers;

import com.solidbeans.call4help.dtos.PositionDTO;
import com.solidbeans.call4help.jms.JmsService;
import com.solidbeans.call4help.notification.AmazonSNSService;
import com.solidbeans.call4help.service.AlertService;
import com.solidbeans.call4help.service.EndpointService;
import com.solidbeans.call4help.service.PositionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class SenderController {

    private final AlertService alertService;
    private final EndpointService endpointService;
    private final AmazonSNSService amazonSNSService;
    private final JmsService jmsService;
    private final PositionService positionService;

    public SenderController(AlertService alertService, EndpointService endpointService,
                            AmazonSNSService amazonSNSService, JmsService jmsService, PositionService positionService) {
        this.alertService = alertService;
        this.endpointService = endpointService;
        this.amazonSNSService = amazonSNSService;
        this.jmsService = jmsService;
        this.positionService = positionService;
    }

//TODO Language of the MESSAGE depending on the language used on the phone
    //TODO Delete published messages after 24 hours

    @PostMapping("/auth/sender/create")
    public ResponseEntity<?> sendHelpRequest(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-Auth-User") String userId, @RequestBody PositionDTO positionDTO) {

        var alert = alertService.registerHelpAlert(userId, positionDTO);

        //Push notification
        amazonSNSService.publishMessage(endpointService.getAllEndpoints(), "Någon ropar på hjälp!");

        //Publish message
        var message = jmsService.publishMessage(positionService.getNearestUsers(alert.getId()));


        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //TODO THIS IS ONLY FOR TEST
    @GetMapping("/alerts")
    public ResponseEntity<?> getALLAlerts(){
        return new ResponseEntity<>(alertService.getAllAlerts(), HttpStatus.OK);
    }

}
