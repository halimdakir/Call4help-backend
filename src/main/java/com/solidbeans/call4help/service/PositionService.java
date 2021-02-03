package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dto.PositionDTO;
import com.solidbeans.call4help.entity.Position;

public interface PositionService {
    Position createUserPosition(PositionDTO position, Long userId);
}
