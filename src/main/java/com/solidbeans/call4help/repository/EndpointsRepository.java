package com.solidbeans.call4help.repository;

import com.solidbeans.call4help.entity.Endpoints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EndpointsRepository extends CrudRepository<Endpoints, Long> {
}
