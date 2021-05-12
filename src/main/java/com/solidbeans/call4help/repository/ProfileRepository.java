package com.solidbeans.call4help.repository;

import com.solidbeans.call4help.dtos.ProfileDTO;
import com.solidbeans.call4help.entities.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long> {

    Optional<Profile> findProfileByUsers_UserId(String userId);

    @Query("SELECT new com.solidbeans.call4help.dtos.ProfileDTO(p.isAnonymous, p.fullName, p.email, p.phoneNumber, p.address, p.postCode, p.ort) FROM Profile p INNER JOIN p.users u WHERE u.id = :id")
    ProfileDTO returnCustomisedProfile(Long id);
}
