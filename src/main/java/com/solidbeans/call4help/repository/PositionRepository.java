package com.solidbeans.call4help.repository;

import com.solidbeans.call4help.dtos.DistanceDTO;
import com.solidbeans.call4help.dtos.DistanceToReportDTO;
import com.solidbeans.call4help.entities.Position;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PositionRepository extends CrudRepository<Position, Long>, PagingAndSortingRepository<Position, Long> {

    Optional<Position> findPositionByProfile_Users_UserId(String userId);

    String query ="SELECT alert.id, ST_Distance_Spheroid(geometry(alert.coordinates), geometry(position.coordinates), 'SPHEROID[\"WGS 84\",6378137,298.257223563]') AS distance FROM position INNER JOIN profile ON position.profile_id = profile.id, alert WHERE position.id = :positionId AND alert.user_id <> profile.user_id AND alert.end_alert_date >= :timeNow AND alert.start_alert_date <= :timeNow";
    @Query(nativeQuery = true, value = query)
    List<DistanceToReportDTO> findDistanceBetweenHelperAndActiveAlerts(Long positionId, ZonedDateTime timeNow);

}
