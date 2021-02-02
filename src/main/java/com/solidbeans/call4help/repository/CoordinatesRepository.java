package com.solidbeans.call4help.repository;

import com.solidbeans.call4help.entity.Coordinates;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CoordinatesRepository extends CrudRepository<Coordinates, Long> {
    Optional<Coordinates> findById(Long id);
}
