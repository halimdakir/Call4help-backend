package com.solidbeans.call4help.controllers;

import com.solidbeans.call4help.dtos.LocationDTO;
import com.solidbeans.call4help.dtos.PositionDTO;
import com.solidbeans.call4help.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/position", produces = MediaType.APPLICATION_JSON_VALUE)
public class PositionController {

    @Autowired
    private PositionService positionService;


    @PostMapping(value = "/create")
    public ResponseEntity<?> registerUserLocation(@Valid @RequestBody PositionDTO positionDTO) {
        return ResponseEntity.ok(positionService.savePosition(positionDTO));
    }
}
