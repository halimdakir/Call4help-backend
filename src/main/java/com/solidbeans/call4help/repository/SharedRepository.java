package com.solidbeans.call4help.repository;

import com.solidbeans.call4help.entity.Shared;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SharedRepository extends CrudRepository<Shared, Long> {
    /*@Query("SELECT DISTINCT ap FROM Shared ap INNER JOIN FETCH ap.user u WHERE u.id=:id")
    Optional<Shared> findAlarmPositionByUserId(Long id);*/
    Optional<Shared> findSharedByUserId(String userId);
}
