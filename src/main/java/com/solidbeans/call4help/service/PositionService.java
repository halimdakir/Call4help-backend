package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dto.DistanceDTO;
import com.solidbeans.call4help.dto.PositionDTO;
import com.solidbeans.call4help.entity.Position;

import java.util.List;

public interface PositionService {
    Position createUserPosition(PositionDTO position, Long userId);
    Position updateUserPosition(String city, String userId);
    List<DistanceDTO> nearestPersonsList(Long id);
    List<Position> getAllPositions();
}
