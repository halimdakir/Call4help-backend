package com.solidbeans.call4help.repository;

import com.solidbeans.call4help.dtos.ProfileModel;
import com.solidbeans.call4help.entities.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long> {

    Optional<Profile> findProfileByUsers_UserId(String userId);

    String query ="SELECT profile.anonymous, profile.full_name, profile.email, profile.phone_number FROM profile INNER JOIN users ON profile.user_id = users.id WHERE users.id = :id";
    @Query(nativeQuery = true, value = query)
    ProfileModel returnCustomisedProfile(Long id);
}
