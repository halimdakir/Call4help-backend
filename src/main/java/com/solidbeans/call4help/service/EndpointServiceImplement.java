package com.solidbeans.call4help.service;

import com.solidbeans.call4help.entity.Endpoints;
import com.solidbeans.call4help.repository.EndpointsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EndpointServiceImplement implements EndpointService{
    @Autowired
    private EndpointsRepository endpointsRepository;


    @Override
    public List<Endpoints> getAllEndpoints() {
        return (List<Endpoints>) endpointsRepository.findAll();
    }

    @Override
    public List<Endpoints> getEndpointsByPosition(String city) {
        return endpointsRepository.findAllByPosition_Municipality(city);
    }
}
