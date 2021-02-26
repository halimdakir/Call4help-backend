package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dtos.PositionDTO;
import com.solidbeans.call4help.entities.Position;

public interface PositionService {
    Position savePosition(PositionDTO positionDTO);
}
