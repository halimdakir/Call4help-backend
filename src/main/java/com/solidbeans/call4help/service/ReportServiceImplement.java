package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dtos.DistanceToReportDTO;
import com.solidbeans.call4help.dtos.ReportDTO;
import com.solidbeans.call4help.dtos.ReportModel;
import com.solidbeans.call4help.entities.Alert;
import com.solidbeans.call4help.entities.Images;
import com.solidbeans.call4help.entities.Report;
import com.solidbeans.call4help.entities.Videos;
import com.solidbeans.call4help.exception.FileStorageException;
import com.solidbeans.call4help.exception.NotFoundException;
import com.solidbeans.call4help.repository.ImageRepository;
import com.solidbeans.call4help.repository.PositionRepository;
import com.solidbeans.call4help.repository.ReportRepository;
import com.solidbeans.call4help.repository.VideoRepository;
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
    private ImageRepository imageRepository;
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AlertService alertService;
    @Autowired
    private PositionRepository positionRepository;


    @Override
    public void saveReport(String userId, ReportDTO reportDTO) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(reportDTO.getFile().getOriginalFilename()));
        LOGGER.info("The file name! {}", fileName);
        //File extension
        String extension = "";
        int index = fileName.lastIndexOf('.');
        if(index > 0) {
            extension = fileName.substring(index + 1);
        }

        LOGGER.info("The file extension! {}", extension);

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
                                if (!reportDTO.getText().equals("")){

                                    var report = new Report(reportDTO.getText(), Instant.now().atZone(ZoneOffset.UTC), reporter, alert.get());
                                    reportRepository.save(report);
                                }
                                if (extension.equalsIgnoreCase("jpg")){

                                    var image = new Images(reportDTO.getFile().getBytes(), Instant.now().atZone(ZoneOffset.UTC), reporter, alert.get());
                                    imageRepository.save(image);

                                }else if (extension.equalsIgnoreCase("mp4")){

                                    var video = new Videos(reportDTO.getFile().getBytes(),Instant.now().atZone(ZoneOffset.UTC), reporter, alert.get());
                                    videoRepository.save(video);
                                }


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
