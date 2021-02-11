package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dto.ReportDTO;
import com.solidbeans.call4help.entity.Report;
import com.solidbeans.call4help.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImplement implements ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Override
    public ReportDTO saveReport(Long userId, String text) {
        var report = new Report(userId, text);
        reportRepository.save(report);
        return ReportDTO.builder()
                .text(text)
                .build();
    }

}
