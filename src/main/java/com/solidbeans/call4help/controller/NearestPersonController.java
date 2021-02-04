package com.solidbeans.call4help.controller;

import com.solidbeans.call4help.dto.DistanceDTO;
import com.solidbeans.call4help.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/nearest")
public class NearestPersonController {

    @Autowired
    private PositionService positionService;

    @GetMapping("/id/{id}")
    public List<DistanceDTO> getNearestPersons(@PathVariable Long id){
        return positionService.nearestPersonsList(id);
    }
}
