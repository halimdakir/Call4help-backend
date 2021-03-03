package com.solidbeans.call4help.repository;

import com.solidbeans.call4help.entities.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {

    @Query("SELECT DISTINCT u FROM Users u WHERE u.userId=:userId")
    Users findUserByUserId(String userId);

    Users findUsersById(Long id);

    Optional<Users> findUsersByAuthToken(String token);

    @Query("SELECT DISTINCT u FROM Users u INNER JOIN FETCH u.location p WHERE p.id=:id")
    Optional<Users> findUsersByLocationId(Long id);

    Optional<Users> findUsersByLocation_Position_Id(Long id);

}
