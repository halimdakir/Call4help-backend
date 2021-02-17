package com.solidbeans.call4help.controller;

import com.solidbeans.call4help.entity.Endpoints;
import com.solidbeans.call4help.service.EndpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/arn", produces = "application/json")
public class EndpointsController {
    @Autowired
    private EndpointService endpointService;

    @GetMapping(value = "/all")
    public List<Endpoints> getAllEndpoints(){
        return endpointService.getAllEndpoints();
    }

    @GetMapping(value = "/city/{city}")
    public List<Endpoints> getEndpointsByCity(@PathVariable String city){
        return endpointService.getEndpointsByPosition(city);
    }
}
