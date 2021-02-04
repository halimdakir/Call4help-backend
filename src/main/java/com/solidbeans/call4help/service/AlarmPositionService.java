package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dto.PositionDTO;
import com.solidbeans.call4help.entity.AlarmPosition;

public interface AlarmPositionService {
    AlarmPosition registerAlarmPosition(PositionDTO position, Long userId);
}
