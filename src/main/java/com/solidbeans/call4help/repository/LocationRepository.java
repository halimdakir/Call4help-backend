package com.solidbeans.call4help.repository;

import com.solidbeans.call4help.entities.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long>{

    Optional<Location> findById(Long id);

    @Query("SELECT DISTINCT l FROM Location l INNER JOIN FETCH l.users u WHERE u.userId=:userId")
    Optional<Location> findLocationByUserId(String userId);

    @Query("SELECT DISTINCT l FROM Location l INNER JOIN FETCH l.users u WHERE l.municipality=:city AND u.userId<>:userId")
    List<Location> findAllByCityAndUserId(String city, String userId);

    @Query("SELECT DISTINCT l FROM Location l")
    List<Location> findAllLocations();

}
