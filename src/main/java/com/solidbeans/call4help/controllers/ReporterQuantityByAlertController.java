package com.solidbeans.call4help.controllers;

import com.solidbeans.call4help.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/sender/report", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReporterQuantityByAlertController {

    @Autowired
    private AlertService alertService;


    @GetMapping("userId/{userId}")
    public ResponseEntity<?> getAlertAndCountReporters(@PathVariable String userId){
        return new ResponseEntity<>( alertService.getReporterQuantityByAlertAndUser(userId), HttpStatus.OK);
    }
}
