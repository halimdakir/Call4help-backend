package com.solidbeans.call4help.repository;

import com.solidbeans.call4help.dtos.ActiveAlertDTO;
import com.solidbeans.call4help.entities.Alert;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;


@Repository
public interface AlertRepository extends CrudRepository<Alert, Long> {
    List<Alert> findAlertByUsers_UserId(String userId);

    @Query("SELECT DISTINCT a FROM Alert a WHERE a.users.userId <> :userId AND a.endAlertDate >= :timeNow AND a.startAlertDate <= :timeNow")
    List<Alert> findAllActiveAlertsExceptMine(String userId, ZonedDateTime timeNow);

    @Query("SELECT DISTINCT a FROM Alert a WHERE a.users.userId = :userId AND a.endAlertDate >= :timeNow AND a.startAlertDate <= :timeNow")
    List<Alert> findMyActiveAlert(String userId, ZonedDateTime timeNow);

}
