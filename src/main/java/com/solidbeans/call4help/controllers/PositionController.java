package com.solidbeans.call4help.controllers;

import com.solidbeans.call4help.dto.PositionDTO;
import com.solidbeans.call4help.notification.AmazonSNSService;
import com.solidbeans.call4help.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/position", produces = MediaType.APPLICATION_JSON_VALUE)
public class PositionController {

    @Autowired
    private PositionService positionService;

    @Autowired
    private AmazonSNSService amazonSNSService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> registerUserPosition(@Valid @RequestBody PositionDTO positionDTO) {
        return ResponseEntity.ok(amazonSNSService.createAwsSnsEndpoint(positionService.createUserPosition(positionDTO)));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllPositions() {
        return ResponseEntity.ok(positionService.getAllPositions());
    }

    @PutMapping("/userId/{userId}")
    public ResponseEntity<?> updateUserPosition(@RequestBody String city, @PathVariable String userId) {
        return ResponseEntity.ok(positionService.updateUserPosition(city, userId));
    }
}
