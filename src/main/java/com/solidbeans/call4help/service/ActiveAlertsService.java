package com.solidbeans.call4help.service;


import com.solidbeans.call4help.entities.Alert;

import java.util.List;

public interface ActiveAlertsService {
    int getActiveAlertsQuantity(String userId);
    List<Alert> activeAlert(String userId);
}
