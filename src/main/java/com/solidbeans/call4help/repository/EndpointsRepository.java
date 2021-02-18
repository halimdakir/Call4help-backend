package com.solidbeans.call4help.repository;

import com.solidbeans.call4help.entity.Endpoints;
import com.solidbeans.call4help.entity.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EndpointsRepository extends CrudRepository<Endpoints, Long> {
    List<Endpoints> findAllByPosition_Municipality(String city);
}
