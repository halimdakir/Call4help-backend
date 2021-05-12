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
import com.solidbeans.call4help.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
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
    @Autowired
    private AlertRepository alertRepository;


    @Override
    public void saveReport(String userId, ReportDTO reportDTO) {

        //Find the helper
        var reporter = userService.findUserByUserId(userId);

        if (reporter != null){

            //Get Helper's position
            var position = positionRepository.findPositionByProfile_Users_UserId(userId);

            if (position.isPresent()){

                //GET ACTIVE ALERTS AND WITHIN 1 KM AS DISTANCE
                List<Alert> activeAlertAndWithin1kmList = getActiveAlertWithin1KmDistance(positionRepository.findDistanceBetweenHelperAndActiveAlerts(position.get().getId(), Instant.now().atZone(ZoneOffset.UTC)));

                if (activeAlertAndWithin1kmList.size() > 0 ) {

                    for (Alert alert : activeAlertAndWithin1kmList){

                        var foundAlert= alertService.findAlertById(alert.getId());

                        if (foundAlert.isPresent()){

                            try{

                                if (!reportDTO.getText().equals("")){   //REPORT TYPE: TEXT

                                    var report = new Report(reportDTO.getText(), Instant.now().atZone(ZoneOffset.UTC), reporter, foundAlert.get());
                                    reportRepository.save(report);

                                }

                                if (reportDTO.getFile() != null){

                                    //GET FILE NAME AND EXTENSION
                                    String fileName = getFileName(reportDTO.getFile());
                                    String extension = getFileExtension(fileName);


                                    if (extension.equalsIgnoreCase("jpg")){    //REPORT TYPE : IMAGE


                                        var image = new Images(reportDTO.getFile().getBytes(), Instant.now().atZone(ZoneOffset.UTC), reporter, foundAlert.get());
                                        imageRepository.save(image);


                                    }else if (extension.equalsIgnoreCase("mp4")){  //REPORT TYPE: VIDEO

                                        var video = new Videos(reportDTO.getFile().getBytes(),Instant.now().atZone(ZoneOffset.UTC), reporter, foundAlert.get());
                                        videoRepository.save(video);

                                    }

                                }

                            }catch (IOException ex) {

                                throw new FileStorageException("Could not store the file. Please try again!", ex);

                            }

                        }else{

                            throw new NotFoundException("Alert not found with the given id!"+alert.getId());

                        }

                    }

                }else{
                    throw new NotFoundException("There no active alert at the moment!");
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


    private String getFileExtension(String fileName){        //File extension
        String extension = "";
        int index = fileName.lastIndexOf('.');
        if(index > 0) {
            extension = fileName.substring(index + 1);
        }
        LOGGER.info("The file extension! {}", extension);
        return extension;
    }

    private String getFileName(MultipartFile file){        // Normalize file name
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        LOGGER.info("The file name! {}", fileName);
        return fileName;
    }
    private List<Alert> getActiveAlertWithin1KmDistance(List<DistanceToReportDTO> list){
        List<Alert> alertList = new ArrayList<>();

        for (DistanceToReportDTO distance : list){

            if (distance.getDistance() <= 1000){
                LOGGER.info("Distance between Helper and Sender's alert! {}",Math.round(distance.getDistance()));
                var alert = alertService.findAlertById(distance.getId());
                alert.ifPresent(alertList::add);

            }

        }
        return alertList;

    }

}
