package com.solidbeans.call4help.repository;

import com.solidbeans.call4help.entities.Videos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends CrudRepository<Videos, Long> {
    List<Videos> findAllByAlert_Id(Long id);
}
