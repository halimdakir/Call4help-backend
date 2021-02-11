package com.solidbeans.call4help.controller;

import com.solidbeans.call4help.service.AlertService;
import com.solidbeans.call4help.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sender")
public class SenderController {

    @Autowired
    private AlertService alertService;
    @Autowired
    private PositionService positionService;



    @GetMapping("userId/{userId}")
    public ResponseEntity<?> sendHelpRequest(@PathVariable String userId){

        alertService.registerHelpAlert(userId);

        //TODO add send notification to the person in the nearest person list ...

        positionService.nearestPersonsList(userId);

        return new ResponseEntity<>("Help has successfully sent!", HttpStatus.OK);
    }
}
