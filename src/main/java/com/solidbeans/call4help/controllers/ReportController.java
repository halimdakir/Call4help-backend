package com.solidbeans.call4help.controllers;

import com.solidbeans.call4help.dtos.ReportDTO;
import com.solidbeans.call4help.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/all")
    public ResponseEntity<?> getAllReports(){
        return new ResponseEntity<>(reportService.allReports(), HttpStatus.OK);
    }
}
