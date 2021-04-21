package com.solidbeans.call4help.repository;

import com.solidbeans.call4help.entities.Alert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AlertRepository extends CrudRepository<Alert, Long> {
    List<Alert> findAlertByUsers_UserId(String userId);
}
