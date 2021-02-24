package com.solidbeans.call4help.repository;

import com.solidbeans.call4help.entities.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long>, PagingAndSortingRepository<Location, Long> {

    Optional<Location> findById(Long id);

    @Query("SELECT DISTINCT l FROM Location l INNER JOIN FETCH l.users u WHERE u.userId=:userId")
    Optional<Location> findLocationByUserId(String userId);

    @Query("SELECT DISTINCT l FROM Location l INNER JOIN FETCH l.users u WHERE l.municipality=:city AND u.userId<>:userId")
    List<Location> findAllByCityAndUserId(String city, String userId);

    @Query("SELECT DISTINCT l FROM Location l")
    List<Location> findAllLocations();

    /*
    String query ="SELECT position.id, ST_Distance_Spheroid(geometry(position.coordinates), geometry(shared.coordinates), 'SPHEROID[\"WGS 84\",6378137,298.257223563]') AS distance FROM position, shared WHERE shared.id = :id and shared.user_id <> position.user_id";
    @Query(nativeQuery = true, value = query)
    List<DistanceDTO> findNearestPersonList(Long id);
     */
}
