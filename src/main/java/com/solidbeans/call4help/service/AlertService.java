package com.solidbeans.call4help.service;

import com.solidbeans.call4help.entities.Alert;

import java.util.Optional;

public interface AlertService {
    Alert registerHelpAlert(String userId);
    Optional<Alert> findAlertById(Long id);

}
