package com.solidbeans.call4help.controllers;

import com.solidbeans.call4help.dtos.PositionDTO;
import com.solidbeans.call4help.service.PositionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/auth/position", produces = MediaType.APPLICATION_JSON_VALUE)
public class PositionController {

    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> saveOrUpdatePosition(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-Auth-User") String userId, @RequestBody PositionDTO positionDTO) {
        positionService.savePosition(userId, positionDTO);
        return ResponseEntity.ok("Done!");
    }
}
