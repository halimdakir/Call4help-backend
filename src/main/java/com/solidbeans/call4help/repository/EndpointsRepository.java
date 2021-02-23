package com.solidbeans.call4help.repository;

import com.solidbeans.call4help.entities.Endpoint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EndpointsRepository extends CrudRepository<Endpoint, Long> {
    List<Endpoint> findAllByPosition_Municipality(String city);
}
