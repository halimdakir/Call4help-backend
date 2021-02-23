package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dtos.PositionDTO;
import com.solidbeans.call4help.entities.Position;

import java.util.List;
import java.util.Optional;

public interface PositionService {

    Position createUserPosition(PositionDTO position);

    Position updateUserPosition(String city, String userId);

    List<Position> getAllPositions();

    Optional<Position> getPositionByUserId(String userId);
}
