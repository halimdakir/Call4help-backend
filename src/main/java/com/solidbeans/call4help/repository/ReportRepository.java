package com.solidbeans.call4help.repository;

import com.solidbeans.call4help.entities.Report;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {

    List<Report> findAllByAlert_Users_UserId(String userId);
}
