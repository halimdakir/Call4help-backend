package com.solidbeans.call4help.controller;

import com.solidbeans.call4help.dto.ReportDTO;
import com.solidbeans.call4help.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping
    public ResponseEntity<ReportDTO> registerReport(Long userId, @RequestBody String text){
        var report = reportService.saveReport(userId, text);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }
}
