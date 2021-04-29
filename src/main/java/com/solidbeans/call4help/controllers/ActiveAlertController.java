package com.solidbeans.call4help.controllers;

import com.solidbeans.call4help.service.AlertDurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/auth/alert", produces = MediaType.APPLICATION_JSON_VALUE)
public class ActiveAlertController {

    @Autowired
    private AlertDurationService alertDurationService;


    @GetMapping("/active/all")
    public ResponseEntity<?> getActiveAlert(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-Auth-User") String userId){
        return new ResponseEntity<>(alertDurationService.getActiveAlerts(userId), HttpStatus.OK);
    }
}
