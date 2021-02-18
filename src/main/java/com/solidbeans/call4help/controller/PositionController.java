package com.solidbeans.call4help.controller;

import com.solidbeans.call4help.dto.PositionDTO;
import com.solidbeans.call4help.entity.Position;
import com.solidbeans.call4help.notification.AmazonSNSService;
import com.solidbeans.call4help.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/position", produces = "application/json")
public class PositionController {

    @Autowired
    private PositionService positionService;
    @Autowired
    private AmazonSNSService amazonSNSService;


    @PostMapping(value = "/create")
    public ResponseEntity<?> registerUserPosition(@Valid @RequestBody PositionDTO positionDTO){

        //Create user's position
            var createdPosition = positionService.createUserPosition(positionDTO);
        //Create user's endpoint ARN
            amazonSNSService.createAwsSnsEndpoint(createdPosition);

            return new ResponseEntity<>( HttpStatus.OK);

    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllPositions(){
        List<Position> list =  positionService.getAllPositions();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping("/userId/{userId}")
    public ResponseEntity<?> updateUserPosition(@RequestBody String city, @PathVariable String userId) {
        Position position = positionService.updateUserPosition(city, userId);
        return new ResponseEntity<>(position, HttpStatus.OK);
    }

}
