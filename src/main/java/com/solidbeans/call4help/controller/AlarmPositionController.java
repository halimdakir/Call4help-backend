package com.solidbeans.call4help.controller;

import com.solidbeans.call4help.dto.PositionDTO;
import com.solidbeans.call4help.exception.RegistrationException;
import com.solidbeans.call4help.service.AlarmPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/alarm/position")
public class AlarmPositionController {

    @Autowired
    private AlarmPositionService alarmPositionService;


    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<?> saveAlarmPosition(@Valid @RequestBody PositionDTO position, Long userId){

        if (position.getCoordinates().getLng()!= null && position.getDateTime()!= null && position.getCoordinates().getLat()!= null){

            var createdAlarmPosition = alarmPositionService.registerAlarmPosition(position, userId);

            return new ResponseEntity<>(createdAlarmPosition, HttpStatus.OK);

        }else {

            throw new RegistrationException("All fields are required!");

        }

    }
}
