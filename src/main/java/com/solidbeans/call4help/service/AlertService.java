package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dtos.PositionDTO;
import com.solidbeans.call4help.entities.Alert;

import java.util.List;
import java.util.Optional;

public interface AlertService {

    Alert registerHelpAlert(String userId, PositionDTO positionDTO);

    Optional<Alert> findAlertById(Long id);

}
