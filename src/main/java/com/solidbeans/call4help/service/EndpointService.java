package com.solidbeans.call4help.service;

import com.solidbeans.call4help.entity.Endpoints;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EndpointService {

    List<Endpoints> getAllEndpoints();

    List<Endpoints> getEndpointsByPosition(String city);
}
