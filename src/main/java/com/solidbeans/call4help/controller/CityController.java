package com.solidbeans.call4help.controller;

import com.solidbeans.call4help.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/cities", produces = MediaType.APPLICATION_JSON_VALUE)
public class CityController {

    @Autowired
    private CityService cityService;

    // a get request should return all cities, no need to have the extension "/all" in
    // order to return all cities because we nothing else than a get all method.
    @GetMapping
    public ResponseEntity<?> getAllCities() {
     return cityService.allCities();
    }
}
