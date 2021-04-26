package com.solidbeans.call4help.service;

import com.solidbeans.call4help.entities.Alert;
import com.solidbeans.call4help.entities.Report;
import com.solidbeans.call4help.model.Evidence;
import com.solidbeans.call4help.repository.AlertRepository;
import com.solidbeans.call4help.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class EvidenceServiceImplement implements EvidenceService{
    @Autowired
    private AlertRepository alertRepository;
    @Autowired
    private ReportRepository reportRepository;


    //TODO IMPLEMENT REPORT IMAGE AND VIDEO
    @Override
    public List<Evidence> getEvidences(String userId) {
        List<Evidence> evidenceList = new ArrayList<>();

        List<Alert> alerts = alertRepository.findAlertByUsers_UserId(userId);

        if (alerts.size() != 0){

            for (Alert alert : alerts) {

                List<Report> reportList = reportRepository.findAllByAlert_Id(alert.getId());
                int countText = 0;
                int countImage = 0;
                int countVideo =0;

                for (Report report : reportList){
                    if (report.getText() != null || !report.getText().equals("")){
                        countText++;
                    }
                    if (report.getImage().length != 0){
                        countImage++;
                    }
                }

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String formattedString = alert.getAlertDate().format(formatter);

                evidenceList.add(new Evidence(formattedString, countText, countImage, 0));
            }


        }
        return evidenceList;
    }
}
