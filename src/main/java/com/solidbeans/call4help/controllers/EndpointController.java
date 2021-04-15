package com.solidbeans.call4help.controllers;

import com.solidbeans.call4help.service.EndpointService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/arn", produces = MediaType.APPLICATION_JSON_VALUE)
public class EndpointController {

    private final EndpointService endpointService;

    public EndpointController(EndpointService endpointService) {
        this.endpointService = endpointService;
    }

    // use parameters when searching.
    // for example localhost:8080/api/v1/arn returns all cities and
    // localhost:8080/api/v1/arn?city=stockholm should return the endpoint for Stockholm
    @GetMapping
    public ResponseEntity<?> getAllEndpoints() {
        return ResponseEntity.ok(endpointService.getAllEndpoints());
    }
}
