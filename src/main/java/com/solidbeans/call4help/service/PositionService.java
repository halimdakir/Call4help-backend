package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dtos.NotificationMessageDTO;
import com.solidbeans.call4help.dtos.PositionDTO;
import com.solidbeans.call4help.entities.Position;

import java.util.List;

public interface PositionService {

    Position savePosition(String userId, PositionDTO positionDTO);
    List<NotificationMessageDTO> getNearestUsers(Long id);
    List<Position> getAllPositions();

}
