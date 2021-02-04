package com.solidbeans.call4help.repository;

import com.solidbeans.call4help.entity.AlarmPosition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlarmPositionRepository extends CrudRepository<AlarmPosition, Long> {
}
