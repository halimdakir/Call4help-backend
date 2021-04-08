package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dtos.ReportDTO;
import com.solidbeans.call4help.entities.Report;

import java.util.List;

public interface ReportService {

    ReportDTO saveReport(String userId, ReportDTO reportDTO);

    List<Report> getReportsByAlert(String userId);
}
