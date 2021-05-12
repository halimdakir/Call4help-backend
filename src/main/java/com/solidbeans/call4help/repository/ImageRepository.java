package com.solidbeans.call4help.repository;

import com.solidbeans.call4help.entities.Images;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends CrudRepository<Images, Long> {
    List<Images> findAllByAlert_Id(Long id);
}
