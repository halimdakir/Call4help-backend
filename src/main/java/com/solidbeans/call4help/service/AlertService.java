package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dtos.ReporterQuantityByAlert;
import com.solidbeans.call4help.entities.Alert;

import java.util.List;
import java.util.Optional;

public interface AlertService {
    Alert registerHelpAlert(String userId);
    Optional<Alert> findAlertById(Long id);
    List<ReporterQuantityByAlert> getReporterQuantityByAlertAndUser(String userId);

}
