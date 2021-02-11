package com.solidbeans.call4help.controller;

import com.solidbeans.call4help.file.Location;
import com.solidbeans.call4help.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/api/v1/cities")
public class CitiesController {

    @Autowired
    private CitiesService citiesService;

    @GetMapping("/all")
    public List<Location> getAllCities(){
     return citiesService.allCities();
    }
}
