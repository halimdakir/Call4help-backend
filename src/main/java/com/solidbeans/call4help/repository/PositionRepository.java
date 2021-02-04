package com.solidbeans.call4help.repository;

import com.solidbeans.call4help.dto.DistanceDTO;
import com.solidbeans.call4help.entity.Position;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PositionRepository extends CrudRepository<Position, Long>, PagingAndSortingRepository<Position, Long> {
    Optional<Position> findById(Long id);

    @Query("SELECT DISTINCT p FROM Position p INNER JOIN FETCH p.users u WHERE u.id=:id")
    Optional<Position> findPositionByUserId(Long id);

    @Query(value ="SELECT position.id, ST_Distance(position.coordinates,alam_position.coordinates) FROM position,alam_position WHERE alam_position.id = :id and ST_Distance <= 1000", nativeQuery = true)
    List<DistanceDTO> findNearestPersonList(@Param("id") Long id);
}
