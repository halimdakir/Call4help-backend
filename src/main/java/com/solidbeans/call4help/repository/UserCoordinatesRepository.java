package com.solidbeans.call4help.repository;

import com.solidbeans.call4help.entity.UserCoordinates;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserCoordinatesRepository extends CrudRepository<UserCoordinates, Long> {
    Optional<UserCoordinates> findById(Long id);
}
