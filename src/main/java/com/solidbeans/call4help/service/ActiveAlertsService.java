package com.solidbeans.call4help.service;


import com.solidbeans.call4help.dtos.ActiveAlertDTO;
import com.solidbeans.call4help.entities.Alert;

import java.util.List;

public interface ActiveAlertsService {
    int getActiveAlertsQuantity(String userId);
    List<ActiveAlertDTO> activeAlertDTO(List<Alert> activeAlertList);
    List<Alert> activeAlert(String userId);
}
