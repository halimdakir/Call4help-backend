package com.solidbeans.call4help.repository;

import com.solidbeans.call4help.dtos.ReporterQuantityByAlert;
import com.solidbeans.call4help.entities.Alert;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface AlertRepository extends CrudRepository<Alert, Long> {

    @Query("SELECT new com.solidbeans.call4help.dtos.ReporterQuantityByAlert( a.id, a.alertDate, a.location, count(r.id)) FROM Alert a INNER JOIN a.reports r INNER JOIN a.users u WHERE u.userId=:userId GROUP BY a.id")
    List<ReporterQuantityByAlert> reporterQuantityByAlertAndUser(String userId);

}
