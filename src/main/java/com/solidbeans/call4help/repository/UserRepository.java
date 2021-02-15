package com.solidbeans.call4help.repository;

import com.solidbeans.call4help.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {

    Users findUserByUserId(String userId);
    Users findUsersById(Long id);
    Optional<Users> findUsersByAuthToken(String token);

}
