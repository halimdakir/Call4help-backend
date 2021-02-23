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

    @GetMapping("/all")
    public ResponseEntity<?> getAllCities() {
     return cityService.allCities();
    }
}
