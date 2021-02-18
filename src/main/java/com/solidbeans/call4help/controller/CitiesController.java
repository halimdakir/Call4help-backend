package com.solidbeans.call4help.controller;

import com.solidbeans.call4help.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/v1/cities", produces = "application/json")
public class CitiesController {

    @Autowired
    private CitiesService citiesService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllCities(){
     var cities = citiesService.allCities();
     return new ResponseEntity<>(cities, HttpStatus.OK);
    }
}
