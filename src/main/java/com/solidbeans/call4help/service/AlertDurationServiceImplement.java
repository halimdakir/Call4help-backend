package com.solidbeans.call4help.service;

import com.solidbeans.call4help.entities.Alert;
import com.solidbeans.call4help.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class AlertDurationServiceImplement implements AlertDurationService {

    @Autowired
    private AlertRepository alertRepository;


    @Override
    public int getActiveAlerts(String userId) {
        return alertRepository.findAlertsExceptMine(userId, Instant.now().atZone(ZoneOffset.UTC)).size();
    }
}
