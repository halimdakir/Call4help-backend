package com.solidbeans.call4help.controllers;

import com.solidbeans.call4help.dtos.PositionDTO;
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

    public SenderController(AlertService alertService, EndpointService endpointService, AmazonSNSService amazonSNSService) {
        this.alertService = alertService;
        this.endpointService = endpointService;
        this.amazonSNSService = amazonSNSService;
    }

    @PostMapping("/auth/sender/create")
    public ResponseEntity<?> sendHelpRequest(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-Auth-User") String userId, @RequestBody PositionDTO positionDTO) {

        var alert = alertService.registerHelpAlert(userId, positionDTO);

        //Push notification
        amazonSNSService.publishMessage(endpointService.getAllEndpoints(), "Någon ropar på hjälp!");



        return new ResponseEntity<>("Done!", HttpStatus.OK);
    }

}
