package com.solidbeans.call4help.service;

import com.solidbeans.call4help.entities.Endpoint;
import com.solidbeans.call4help.exception.NotFoundException;
import com.solidbeans.call4help.repository.EndpointsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EndpointServiceImplement implements EndpointService{
    @Autowired
    private EndpointsRepository endpointsRepository;

    @Autowired
    private LocationService locationService;

    @Override
    public List<Endpoint> getAllEndpoints() {
        return (List<Endpoint>) endpointsRepository.findAll();
    }

    @Override
    public List<Endpoint> getEndpointsByLocation(String userId) {

        var location = locationService.getLocationByUserId(userId);

        if (location.isPresent()){

            return endpointsRepository.findAllByLocation_Municipality(location.get().getMunicipality());

        }else {

            throw new NotFoundException("There no user with id :"+ userId);

        }

    }
}
