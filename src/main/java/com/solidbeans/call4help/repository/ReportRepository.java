package com.solidbeans.call4help.repository;

import com.solidbeans.call4help.entity.Report;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {
}
