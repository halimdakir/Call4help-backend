package com.solidbeans.call4help.controllers;

import com.solidbeans.call4help.dtos.PositionDTO;
import com.solidbeans.call4help.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/auth/position", produces = MediaType.APPLICATION_JSON_VALUE)
public class PositionController {

    @Autowired
    private PositionService positionService;


    @PostMapping(value = "/create")
    public ResponseEntity<?> saveOrUpdatePosition(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-Auth-User") String userId, @RequestBody PositionDTO positionDTO) {
        return ResponseEntity.ok(positionService.savePosition(userId, positionDTO));
    }

    //TODO THIS IS ONLY FOR TEST
    @GetMapping
    public ResponseEntity<?> GetAllPositions(){
        return ResponseEntity.ok(positionService.getAllPositions());
    }
}
