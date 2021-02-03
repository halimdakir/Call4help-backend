package com.solidbeans.call4help.controller;

import com.solidbeans.call4help.dto.PositionDTO;
import com.solidbeans.call4help.exception.RegistrationException;
import com.solidbeans.call4help.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth/position")
public class PositionController {

    @Autowired
    private PositionService positionService;


    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<PositionDTO> saveLastPosition(@Valid @RequestBody PositionDTO position, Long userId){

        if (position.getCoordinates().getLat()!= null && position.getCoordinates().getLng()!= null && position.getDateTime()!= null){

            positionService.createUserPosition(position, userId);
            return new ResponseEntity<>(position, HttpStatus.OK);

        }else {

            throw new RegistrationException("All fields are required!");

        }

    }

}
