package com.solidbeans.call4help.controller;

import com.solidbeans.call4help.notification.AmazonSNSService;
import com.solidbeans.call4help.notification.PlatformEndpointDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/aws/create", produces = "application/json")
public class AwsCreateEndpointController {

    @Autowired
    private AmazonSNSService amazonSNSService;


    @PostMapping
    public ResponseEntity<?> createAwsEndpoint(@RequestBody PlatformEndpointDTO platformEndpoint){
        var result = amazonSNSService.createAwsSnsEndpoint(platformEndpoint);
            return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
