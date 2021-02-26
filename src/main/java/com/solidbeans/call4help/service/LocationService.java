package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dtos.LocationDTO;
import com.solidbeans.call4help.entities.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {

    Location createUserLocation(LocationDTO position);

    Location updateUserLocation(String city, String userId);

    List<Location> getAllLocations();

    Optional<Location> getLocationByUserId(String userId);
}
