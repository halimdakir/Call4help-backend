package com.solidbeans.call4help.controllers;

import com.solidbeans.call4help.dtos.ReportDTO;
import com.solidbeans.call4help.jms.JmsConsumer;
import com.solidbeans.call4help.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/report", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping
    public ResponseEntity<?> registerReport(@RequestBody ReportDTO reportDTO){
        var report = reportService.saveReport(reportDTO);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }


    //IT'S DONE REPORTING ... SO WE NEED TO DELETE MESSAGE AND CLOSE
    @PostMapping("/done")
    public ResponseEntity<?> registerReportAndClose(@RequestBody ReportDTO reportDTO, @RequestBody String uuid){

        var report = reportService.saveReport(reportDTO);

        UUID stringToUuid = UUID.fromString(uuid);

        //Find message and deleted
        var message = JmsConsumer.Jms_Message_List.stream()
                .filter(e -> e.getUuid().equals(stringToUuid))
                .collect(Collectors.toList());
        if (message.size() > 0){
            JmsConsumer.Jms_Message_List.remove(message.get(0));
        }

        return new ResponseEntity<>(report, HttpStatus.OK);
    }


    //REFUSE TO REPORT
    @PostMapping("/done")
    public ResponseEntity<?> refuseReport(@RequestBody String uuid){

        UUID stringToUuid = UUID.fromString(uuid);

        //Find message and deleted
        var message = JmsConsumer.Jms_Message_List.stream()
                .filter(e -> e.getUuid().equals(stringToUuid))
                .collect(Collectors.toList());
        if (message.size() == 1){
            JmsConsumer.Jms_Message_List.remove(message.get(0));
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //TODO GET REPORTS BY ALERT, AND THIS ENDPOINT WONT BE AVAILABLE TO USERS.
    @GetMapping("userId/{userId}")
    public ResponseEntity<?> getReportsByAlarm(@PathVariable String userId){
        return new ResponseEntity<>(reportService.getReportsByAlert(userId), HttpStatus.OK);
    }
}
