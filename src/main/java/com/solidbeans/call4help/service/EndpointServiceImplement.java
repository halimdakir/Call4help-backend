package com.solidbeans.call4help.service;

import com.solidbeans.call4help.entities.Endpoint;
import com.solidbeans.call4help.repository.EndpointsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EndpointServiceImplement implements EndpointService{
    @Autowired
    private EndpointsRepository endpointsRepository;


    @Override
    public List<Endpoint> getAllEndpoints() {
        return (List<Endpoint>) endpointsRepository.findAll();
    }

}
