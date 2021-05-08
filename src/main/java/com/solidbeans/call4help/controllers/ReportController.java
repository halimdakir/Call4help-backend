package com.solidbeans.call4help.controllers;

import com.solidbeans.call4help.dtos.PositionDTO;
import com.solidbeans.call4help.dtos.ReportDTO;
import com.solidbeans.call4help.jms.JmsConsumer;
import com.solidbeans.call4help.jms.JmsService;
import com.solidbeans.call4help.service.PositionService;
import com.solidbeans.call4help.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReportController {

    private final ReportService reportService;
    private final JmsService jmsService;

    public ReportController(ReportService reportService, JmsService jmsService) {
        this.reportService = reportService;
        this.jmsService = jmsService;
    }

    @PostMapping(value = "/auth/report")
    public ResponseEntity<?> saveReport(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-Auth-User") String userId, @RequestParam("file") MultipartFile file, @RequestParam("text") String text){
        ReportDTO reportDTO = new ReportDTO(text, file);
        reportService.saveReport(userId, reportDTO);
        return new ResponseEntity<>("Done!", HttpStatus.OK);
    }


    //IT'S DONE REPORTING ... SO WE NEED TO DELETE MESSAGE AND CLOSE
    @PostMapping("/done")
    public ResponseEntity<?> registerReportAndClose(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-Auth-User") String userId, @RequestBody ReportDTO reportDTO, @RequestBody String uuid){

        //var report =
                reportService.saveReport(userId, reportDTO);

        //Find message and delete it
        jmsService.findMessageByUuidAndRemove(uuid);

        return new ResponseEntity<>( HttpStatus.OK);
    }


    //REFUSE TO REPORT
    @PostMapping("/refuse")
    public ResponseEntity<?> refuseReport(@RequestBody String uuid){

        //Find message and delete it
        jmsService.findMessageByUuidAndRemove(uuid);

        return new ResponseEntity<>(HttpStatus.OK);
    }



    //TODO GET REPORTS BY ALERT, AND THIS ENDPOINT WONT BE AVAILABLE TO USERS.
    @GetMapping("userId/{userId}")
    public ResponseEntity<?> getReportsByAlarm(@PathVariable String userId){
        return new ResponseEntity<>(reportService.getReportsByAlert(userId), HttpStatus.OK);
    }


    //TODO ONLY FOR TEST
    @GetMapping("/report/all")
    public ResponseEntity<?> getAllReports(){
        return new ResponseEntity<>(reportService.getAllReports(), HttpStatus.OK);
    }

}
