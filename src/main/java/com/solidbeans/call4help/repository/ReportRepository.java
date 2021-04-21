package com.solidbeans.call4help.repository;

import com.solidbeans.call4help.dtos.ReportModel;
import com.solidbeans.call4help.entities.Report;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {

    List<Report> findAllByAlert_Users_UserId(String userId);

    String query ="SELECT report.id, report.text, report.date_time, report.helper_id, report.alert_id  FROM report";
    @Query(nativeQuery = true, value = query)
    List<ReportModel> allReports();

    List<Report> findAllByAlert_Id(Long id);
}