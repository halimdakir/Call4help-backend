package com.solidbeans.call4help.repository;

import com.solidbeans.call4help.entity.Position;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PositionRepository extends CrudRepository<Position, Long> {
    Optional<Position> findById(Long id);
}
