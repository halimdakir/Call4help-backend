package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dtos.ReportDTO;
import com.solidbeans.call4help.dtos.ReportModel;
import com.solidbeans.call4help.entities.Report;
import com.solidbeans.call4help.exception.FileStorageException;
import com.solidbeans.call4help.exception.NotFoundException;
import com.solidbeans.call4help.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;

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
        // Normalize file name
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(reportDTO.getFile().getOriginalFilename()));

        //Find user who has reported
        var reporter = userService.findUserByUserId(userId);

        //Find alert to whom has reported
        var alert = alertService.findAlertById(reportDTO.getAlertId());

        if (alert.isPresent() && reporter != null){

            try{

                var report = new Report(reportDTO.getText(), reportDTO.getFile().getBytes(),Instant.now().atZone(ZoneOffset.UTC), reporter, alert.get());
                reportRepository.save(report);

                return ReportDTO.builder()
                        .text(reportDTO.getText())
                        .alertId(reportDTO.getAlertId())
                        .build();

            }catch (IOException ex) {

                throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);

            }



        }else {

            throw new NotFoundException("Helper and/or Alert not found with the given id!");

        }




    }

    @Override
    public List<Report> getReportsByAlert(String userId) {
        return reportRepository.findAllByAlert_Users_UserId(userId);
    }

    @Override
    public List<ReportModel> getAllReports() {
        return reportRepository.allReports();
    }

}
