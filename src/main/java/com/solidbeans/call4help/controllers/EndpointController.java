package com.solidbeans.call4help.controllers;

import com.solidbeans.call4help.service.EndpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/arn", produces = MediaType.APPLICATION_JSON_VALUE)
public class EndpointController {

    @Autowired
    private EndpointService endpointService;

    // use parameters when searching.
    // for example localhost:8080/api/v1/arn returns all cities and
    // localhost:8080/api/v1/arn?city=stockholm should return the endpoint for Stockholm
    @GetMapping
    public ResponseEntity<?> getEndpointsByCity(@RequestParam(required = false) String city) {
        if (city.isEmpty()) return ResponseEntity.ok(endpointService.getAllEndpoints());
        else return ResponseEntity.ok(endpointService.getEndpointsByPosition(city));
    }
}
