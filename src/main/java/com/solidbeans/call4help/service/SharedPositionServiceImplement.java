package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dto.PositionDTO;
import com.solidbeans.call4help.entity.Shared;
import com.solidbeans.call4help.exception.NotFoundException;
import com.solidbeans.call4help.repository.SharedRepository;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Service
public class SharedPositionServiceImplement implements SharedPositionService {

    @Autowired
    private UserService userService;
    @Autowired
    private SharedRepository repository;

    private final static GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 26910);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Shared registerAlarmPosition(PositionDTO positionDTO, Long userId) {
        var foundUser = userService.findUserById(userId);
        Shared shared = new Shared();

        if (foundUser==null){

            throw new NotFoundException("User with id :"+userId+" is not found");

        }else {

            shared.setDateTime(positionDTO.getDateTime());
            shared.setCoordinates(geometryFactory.createPoint(new Coordinate(positionDTO.getCoordinates().getLng(), positionDTO.getCoordinates().getLat())));

            //Keep only the last position for every user

            deletePreviousAlarmPosition(foundUser.getUserId());

             repository.save(new Shared(foundUser.getUserId(), shared.getDateTime(), shared.getCoordinates()));

             entityManager.detach(shared);

            return shared;

        }
    }

    @Override
    public List<Shared> getAllAlarmPosition() {
        return (List<Shared>) repository.findAll();
    }

    private void deletePreviousAlarmPosition(String id){
        //var alarmPosition = repository.findAlarmPositionByUserId(id);
        var alarmPosition = repository.findSharedByUserId(id);
        alarmPosition.ifPresent(value -> repository.deleteById(alarmPosition.get().getId()));
    }
}
