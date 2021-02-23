package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dtos.ReportDTO;
import com.solidbeans.call4help.entities.Report;
import com.solidbeans.call4help.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImplement implements ReportService {

    @Autowired
    ReportRepository reportRepository;
    @Autowired
    private UserService userService;

    @Override
    public ReportDTO saveReport(ReportDTO reportDTO) {
        var helper = userService.findUserByUserId(reportDTO.getUserId());
        var sender = userService.findUserById(reportDTO.getSenderId());
        var report = new Report(reportDTO.getText(), helper, sender);
        reportRepository.save(report);
        return ReportDTO.builder()
                .text(reportDTO.getText())
                .userId(reportDTO.getUserId())
                .senderId(reportDTO.getSenderId())
                .build();
    }

}
