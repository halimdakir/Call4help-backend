package com.solidbeans.call4help.repository;

import com.solidbeans.call4help.entity.Position;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PositionRepository extends CrudRepository<Position, Long> {
    Optional<Position> findById(Long id);

    @Query("SELECT DISTINCT p FROM Position p INNER JOIN FETCH p.users u WHERE u.id=:id")
    Optional<Position> findPositionByUserId(Long id);

}
