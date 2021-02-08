package com.solidbeans.call4help.repository;

import com.solidbeans.call4help.entity.Alert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends CrudRepository<Alert, Long> {
}
