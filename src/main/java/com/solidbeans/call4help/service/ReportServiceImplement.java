package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dtos.ReportDTO;
import com.solidbeans.call4help.entities.Report;
import com.solidbeans.call4help.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneOffset;

@Service
public class ReportServiceImplement implements ReportService {

    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private UserService userService;

    @Override
    public ReportDTO saveReport(ReportDTO reportDTO) {

        //Find user who has reported
        var helper = userService.findUserByUserId(reportDTO.getUserId());

        //Find user to whom has reported
        var sender = userService.findUserById(reportDTO.getSenderId());

        var report = new Report(reportDTO.getText(), Instant.now().atZone(ZoneOffset.UTC), helper, sender);
        reportRepository.save(report);

        return ReportDTO.builder()
                .text(reportDTO.getText())
                .userId(reportDTO.getUserId())
                .senderId(reportDTO.getSenderId())
                .build();
    }

}
