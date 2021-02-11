package com.solidbeans.call4help.controller;

import com.solidbeans.call4help.dto.PositionDTO;
import com.solidbeans.call4help.entity.Position;
import com.solidbeans.call4help.exception.RegistrationException;
import com.solidbeans.call4help.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/position")
public class PositionController {

    @Autowired
    private PositionService positionService;


    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<?> registerUserPosition(@Valid @RequestBody PositionDTO position, Long userId){

        if (position.getCoordinates().getLat()!= null && position.getCoordinates().getLng()!= null && position.getDateTime()!= null){

            var createdPosition = positionService.createUserPosition(position, userId);

            return new ResponseEntity<>(createdPosition, HttpStatus.OK);

        }else {

            throw new RegistrationException("All fields are required!");

        }

    }

    @GetMapping(value = "/all", produces = "application/json")
    public List<Position> getAllPositions(){
        return positionService.getAllPositions();
    }

    @PutMapping("/userId/{userId}")
    public Position updateUserPosition(@RequestBody String city, @PathVariable String userId) {
        return positionService.updateUserPosition(city, userId);
    }

}
