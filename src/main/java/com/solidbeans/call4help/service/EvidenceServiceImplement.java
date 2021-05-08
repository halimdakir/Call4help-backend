package com.solidbeans.call4help.service;

import com.solidbeans.call4help.entities.Alert;
import com.solidbeans.call4help.entities.Images;
import com.solidbeans.call4help.entities.Report;
import com.solidbeans.call4help.entities.Videos;
import com.solidbeans.call4help.model.Evidence;
import com.solidbeans.call4help.repository.AlertRepository;
import com.solidbeans.call4help.repository.ImageRepository;
import com.solidbeans.call4help.repository.ReportRepository;
import com.solidbeans.call4help.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
public class EvidenceServiceImplement implements EvidenceService{
    @Autowired
    private AlertRepository alertRepository;
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private VideoRepository videoRepository;


    //TODO IMPLEMENT REPORT IMAGE AND VIDEO
    @Override
    public List<Evidence> getEvidences(String userId) {
        List<Evidence> evidenceList = new ArrayList<>();

        List<Alert> alerts = alertRepository.findAlertByUsers_UserId(userId);

        if (alerts.size() != 0){

            for (Alert alert : alerts) {

                List<Report> textList = reportRepository.findAllByAlert_Id(alert.getId());
                List<Images> imagesList = imageRepository.findAllByAlert_Id(alert.getId());
                List<Videos> videosList = videoRepository.findAllByAlert_Id(alert.getId());


                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                String timeFormattedString = alert.getStartAlertDate().format(timeFormatter);

                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String dateFormattedString = alert.getStartAlertDate().format(dateFormatter);


                evidenceList.add(new Evidence(timeFormattedString, dateFormattedString, textList.size(), imagesList.size(), videosList.size()));
            }


        }
        return evidenceList;
    }
}
