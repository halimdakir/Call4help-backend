package com.solidbeans.call4help.controllers;

import com.solidbeans.call4help.service.EvidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class EvidenceController {
    @Autowired
    private EvidenceService evidenceService;


    @GetMapping("/auth/evidence")
    public ResponseEntity<?> getEvidences(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-Auth-User") String userId){
        return new ResponseEntity<>(evidenceService.getEvidences(userId), HttpStatus.OK);
    }
}
