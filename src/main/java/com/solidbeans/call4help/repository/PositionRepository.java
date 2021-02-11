package com.solidbeans.call4help.repository;

import com.solidbeans.call4help.entity.Position;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PositionRepository extends CrudRepository<Position, Long>, PagingAndSortingRepository<Position, Long> {
    Optional<Position> findById(Long id);

    @Query("SELECT DISTINCT p FROM Position p INNER JOIN FETCH p.users u WHERE u.userId=:userId")
    Optional<Position> findPositionByUserId(String userId);

    @Query("SELECT DISTINCT p FROM Position p INNER JOIN FETCH p.users u WHERE p.municipality=:city AND u.userId<>:userId")
    List<Position> findAllByCityAndUserId(String city, String userId);

    @Query("SELECT DISTINCT p FROM Position p")
    List<Position> findAllPosition();

    /*
    String query ="SELECT position.id, ST_Distance_Spheroid(geometry(position.coordinates), geometry(shared.coordinates), 'SPHEROID[\"WGS 84\",6378137,298.257223563]') AS distance FROM position, shared WHERE shared.id = :id and shared.user_id <> position.user_id";
    @Query(nativeQuery = true, value = query)
    List<DistanceDTO> findNearestPersonList(Long id);
     */
}
