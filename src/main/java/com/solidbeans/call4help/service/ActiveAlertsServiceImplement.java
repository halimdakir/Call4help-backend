package com.solidbeans.call4help.service;

import com.solidbeans.call4help.entities.Alert;
import com.solidbeans.call4help.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class ActiveAlertsServiceImplement implements ActiveAlertsService {

    @Autowired
    private AlertRepository alertRepository;


    @Override
    public int getActiveAlertsQuantity(String userId) {
        return alertRepository.findAllActiveAlertsExceptMine(userId, Instant.now().atZone(ZoneOffset.UTC)).size();
    }

    @Override
    public List<Alert> activeAlert(String userId) {
        return alertRepository.findMyActiveAlert(userId, Instant.now().atZone(ZoneOffset.UTC));
    }
}
