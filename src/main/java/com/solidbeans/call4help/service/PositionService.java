package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dtos.DistanceToMeters;
import com.solidbeans.call4help.dtos.PositionDTO;
import com.solidbeans.call4help.entities.Position;

import java.util.List;

public interface PositionService {

    Position savePosition(PositionDTO positionDTO);
    List<DistanceToMeters> getDistanceBetweenUsers(Long id);
    List<Position> getAllPositions();

}
