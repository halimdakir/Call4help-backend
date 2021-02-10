package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dto.ReportDTO;

public interface ReportService {

    ReportDTO saveReport(Long userId, String text);
}
