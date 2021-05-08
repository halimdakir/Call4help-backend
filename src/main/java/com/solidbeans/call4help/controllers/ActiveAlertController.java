package com.solidbeans.call4help.controllers;

import com.solidbeans.call4help.service.ActiveAlertsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auth/alert", produces = MediaType.APPLICATION_JSON_VALUE)
public class ActiveAlertController {

    @Autowired
    private ActiveAlertsService activeAlertsService;


    @GetMapping("/active/all")
    public ResponseEntity<?> getActiveAlertsQuantity(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-Auth-User") String userId){
        return new ResponseEntity<>(activeAlertsService.getActiveAlertsQuantity(userId), HttpStatus.OK);
    }
    @GetMapping("/active")
    public ResponseEntity<?> getActiveAlert(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-Auth-User") String userId){
        return new ResponseEntity<>(activeAlertsService.activeAlertDTO(activeAlertsService.activeAlert(userId)), HttpStatus.OK);
    }
}
