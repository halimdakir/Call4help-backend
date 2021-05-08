package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dtos.ActiveAlertDTO;
import com.solidbeans.call4help.entities.Alert;
import com.solidbeans.call4help.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    public List<ActiveAlertDTO> activeAlertDTO(List<Alert> activeAlertList) {
        List<ActiveAlertDTO> activeAlertDTOList = new ArrayList<>();

        for (Alert alert : activeAlertList){
            activeAlertDTOList.add(
                    new ActiveAlertDTO(alert.getId(), dateFormatter(alert.getStartAlertDate()), timeFormatter(alert.getStartAlertDate()), dateFormatter(alert.getEndAlertDate()), timeFormatter(alert.getEndAlertDate()), alert.getUsers().getUserId()));
        }

        return activeAlertDTOList;
    }
    @Override
    public List<Alert> activeAlert(String userId) {
        return alertRepository.findMyActiveAlert(userId, Instant.now().atZone(ZoneOffset.UTC));
    }

    private String dateFormatter(ZonedDateTime zonedDateTime){
        return zonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    private String timeFormatter(ZonedDateTime zonedDateTime){
        return zonedDateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
