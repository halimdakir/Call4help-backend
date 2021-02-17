package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dto.PositionDTO;
import com.solidbeans.call4help.dto.UsersDTO;
import com.solidbeans.call4help.entity.Position;

import java.util.List;
import java.util.Optional;

public interface PositionService {
    Position createUserPosition(PositionDTO position);
    Position updateUserPosition(String city, String userId);
    List<UsersDTO> nearestPersonsList(String userId);
    List<Position> getAllPositions();
}
