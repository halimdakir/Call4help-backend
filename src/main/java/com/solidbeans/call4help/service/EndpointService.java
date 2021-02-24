package com.solidbeans.call4help.service;

import com.solidbeans.call4help.entities.Endpoint;

import java.util.List;

public interface EndpointService {

    List<Endpoint> getAllEndpoints();

    List<Endpoint> getEndpointsByLocation(String city);
}
