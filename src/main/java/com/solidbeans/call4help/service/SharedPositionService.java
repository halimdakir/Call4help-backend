package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dto.PositionDTO;
import com.solidbeans.call4help.entity.Shared;

import java.util.List;

public interface SharedPositionService {
    Shared registerAlarmPosition(PositionDTO position, Long userId);

    List<Shared> getAllAlarmPosition();
}
