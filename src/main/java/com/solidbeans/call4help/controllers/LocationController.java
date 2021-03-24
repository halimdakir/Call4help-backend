package com.solidbeans.call4help.controllers;

import com.solidbeans.call4help.dtos.LocationDTO;
import com.solidbeans.call4help.notification.AmazonSNSService;
import com.solidbeans.call4help.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/location", produces = MediaType.APPLICATION_JSON_VALUE)
public class LocationController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private AmazonSNSService amazonSNSService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> registerUserLocation(@Valid @RequestBody LocationDTO locationDTO) {
        return ResponseEntity.ok(amazonSNSService.createAwsSnsEndpoint(locationService.createUserLocation(locationDTO)));
    }

    @PutMapping("/userId/{userId}")
    public ResponseEntity<?> updateUserLocation(@RequestBody String city, @PathVariable String userId) {
        return ResponseEntity.ok(locationService.updateUserLocation(city, userId));
    }

    //TODO THIS IS ONLY FOR TEST
    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllLocations() {
        return ResponseEntity.ok(locationService.getAllLocations());
    }
}
