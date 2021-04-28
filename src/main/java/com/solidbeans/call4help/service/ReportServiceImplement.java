package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dtos.DistanceToReportDTO;
import com.solidbeans.call4help.dtos.ReportDTO;
import com.solidbeans.call4help.dtos.ReportModel;
import com.solidbeans.call4help.entities.Alert;
import com.solidbeans.call4help.entities.Report;
import com.solidbeans.call4help.exception.NotFoundException;
import com.solidbeans.call4help.exception.FileStorageException;
import com.solidbeans.call4help.repository.AlertRepository;
import com.solidbeans.call4help.repository.PositionRepository;
import com.solidbeans.call4help.repository.ReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(ReportServiceImplement.class);

    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AlertService alertService;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private AlertRepository alertRepository;


    @Override
    public void saveReport(String userId, ReportDTO reportDTO) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(reportDTO.getFile().getOriginalFilename()));


        //Find user who has reported
        var reporter = userService.findUserByUserId(userId);

        //Find alert to whom has reported
        List<Alert> alertList = alertService.getAllAlerts();

        if (alertList.size() != 0 && reporter != null){

            //CHECK THE DISTANCE IF 1KM

            var position = positionRepository.findPositionByProfile_Users_UserId(userId);

            if (position.isPresent()){

                List<DistanceToReportDTO> distanceDTOList = positionRepository.findDistanceBetweenSenderAndHelper(position.get().getId());

                for (DistanceToReportDTO distance : distanceDTOList){

                    LOGGER.info("Distance between Helper and Sender! {}", Math.round(distance.getDistance()));

                    var alert= alertService.findAlertById(distance.getId());

                    if (alert.isPresent()){

                        var dateNow = Instant.now().atZone(ZoneOffset.UTC);

                        if (distance.getDistance() <= 1000 && dateNow.isBefore(alert.get().getEndAlertDate()) && dateNow.isAfter(alert.get().getStartAlertDate()) ){

                            try{

                                    var report = new Report(reportDTO.getText(), reportDTO.getFile().getBytes(),Instant.now().atZone(ZoneOffset.UTC), reporter, alert.get());

                                    reportRepository.save(report);


                            }catch (IOException ex) {

                                throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);

                            }
                        }
                    }

                }


            }else {

                throw new NotFoundException("Position not found with the given id!");

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
