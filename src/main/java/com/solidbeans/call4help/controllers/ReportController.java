package com.solidbeans.call4help.controllers;

import com.solidbeans.call4help.dtos.ReportDTO;
import com.solidbeans.call4help.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReportController {

    @Autowired
    private ReportService reportService;


    //REPORT TEXT AND FILE
    @PostMapping(value = "/auth/report/all")
    public ResponseEntity<?> saveReportTextAndFile(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-Auth-User") String userId, @RequestParam("file") MultipartFile file, @RequestParam("text") String text){
        ReportDTO reportDTO = new ReportDTO(text, file);
        reportService.saveReport(userId, reportDTO);
        return new ResponseEntity<>("Done!", HttpStatus.OK);
    }


    //REPORT ONLY TEXT
    @PostMapping("/auth/report/text")
    public ResponseEntity<?> saveReportOnlyText(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-Auth-User") String userId, @RequestParam("text") String text){
        ReportDTO reportDTO = new ReportDTO(text, null);
        reportService.saveReport(userId, reportDTO);
        return new ResponseEntity<>("Done!", HttpStatus.OK);
    }

}
