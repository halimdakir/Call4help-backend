package com.solidbeans.call4help.repository;

import com.solidbeans.call4help.entities.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long> {

    Optional<Profile> findProfileByUsers_UserId(String userId);
}
