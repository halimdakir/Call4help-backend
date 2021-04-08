package com.solidbeans.call4help.repository;

import com.solidbeans.call4help.dtos.DistanceDTO;
import com.solidbeans.call4help.entities.Position;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PositionRepository extends CrudRepository<Position, Long>, PagingAndSortingRepository<Position, Long> {

    Optional<Position> findPositionByProfile_Users_UserId(String userId);

    String query ="SELECT position.id, ST_Distance_Spheroid(geometry(position.coordinates), geometry(alert.coordinates), 'SPHEROID[\"WGS 84\",6378137,298.257223563]') AS distance FROM position INNER JOIN profile ON position.profile_id = profile.id, alert WHERE alert.id = :id and alert.user_id <> profile.user_id";
    @Query(nativeQuery = true, value = query)
    List<DistanceDTO> findNearestPersonList(Long id);


}
