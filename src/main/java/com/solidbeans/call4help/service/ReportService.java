package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dtos.ReportDTO;
import com.solidbeans.call4help.entities.Report;

import java.util.List;

public interface ReportService {

    ReportDTO saveReport(ReportDTO reportDTO);

    List<Report> allReports();
}
