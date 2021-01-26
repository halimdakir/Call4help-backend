package com.solidbeans.call4help.repository;

import com.solidbeans.call4help.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByUserId(String userId);
}
