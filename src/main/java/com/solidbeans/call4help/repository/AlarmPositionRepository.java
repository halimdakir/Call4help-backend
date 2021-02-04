package com.solidbeans.call4help.repository;

import com.solidbeans.call4help.entity.AlarmPosition;
import com.solidbeans.call4help.entity.Position;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlarmPositionRepository extends CrudRepository<AlarmPosition, Long> {
    @Query("SELECT DISTINCT ap FROM AlarmPosition ap INNER JOIN FETCH ap.users u WHERE u.id=:id")
    Optional<AlarmPosition> findAlarmPositionByUserId(Long id);
}
