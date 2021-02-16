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
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/position")
public class PositionController {

    @Autowired
    private PositionService positionService;


    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<?> registerUserPosition(@Valid @RequestBody PositionDTO positionDTO){

        if (positionDTO.getMunicipality() == null || positionDTO.getMunicipality().equals("")){
            throw new RegistrationException("All fields are required!");

        }else {
            var position = new Position();
            position.setDateTime(ZonedDateTime.now(ZoneId.of("UTC")));
            position.setMunicipality(positionDTO.getMunicipality());

            var createdPosition = positionService.createUserPosition(positionDTO);

            return new ResponseEntity<>(createdPosition, HttpStatus.OK);

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
