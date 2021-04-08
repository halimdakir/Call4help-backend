package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dtos.ReportDTO;
import com.solidbeans.call4help.entities.Report;
import com.solidbeans.call4help.exception.NotFoundException;
import com.solidbeans.call4help.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class ReportServiceImplement implements ReportService {

    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AlertService alertService;


    @Override
    public ReportDTO saveReport(String userId, ReportDTO reportDTO) {

        //Find user who has reported
        var helper = userService.findUserByUserId(userId);

        //Find alert to whom has reported
        var alert = alertService.findAlertById(reportDTO.getAlertId());

        if (alert.isPresent() && helper != null){

            var report = new Report(reportDTO.getText(), Instant.now().atZone(ZoneOffset.UTC), helper, alert.get());
            reportRepository.save(report);

            return ReportDTO.builder()
                    .text(reportDTO.getText())
                    .alertId(reportDTO.getAlertId())
                    .build();

        }else {

            throw new NotFoundException("Helper and/or Alert not found with the given id!");

        }




    }

    @Override
    public List<Report> getReportsByAlert(String userId) {
        return (List<Report>) reportRepository.findAllByAlert_Users_UserId(userId);
    }

}
