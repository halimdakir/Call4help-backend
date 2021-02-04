package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dto.PositionDTO;
import com.solidbeans.call4help.entity.AlarmPosition;
import com.solidbeans.call4help.entity.Position;
import com.solidbeans.call4help.entity.Users;
import com.solidbeans.call4help.exception.NotFoundException;
import com.solidbeans.call4help.repository.AlarmPositionRepository;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AlarmPositionServiceImplement implements AlarmPositionService {

    @Autowired
    private UserService userService;

    @Autowired
    private AlarmPositionRepository repository;

    private final static GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 26910);


    @Override
    public AlarmPosition registerAlarmPosition(PositionDTO positionDTO, Long userId) {
        var foundUser = userService.findUserById(userId);
        AlarmPosition alarmPosition = new AlarmPosition();

        if (foundUser==null){

            throw new NotFoundException("User with id :"+userId+" is not found");

        }else {

            alarmPosition.setDateTime(positionDTO.getDateTime());
            alarmPosition.setCoordinates(geometryFactory.createPoint(new Coordinate(positionDTO.getCoordinates().getLng(), positionDTO.getCoordinates().getLat())));

            //Keep only the last position for every user

            deletePreviousPosition(userId);

            return repository.save(new AlarmPosition(alarmPosition.getDateTime(), alarmPosition.getCoordinates(), foundUser));

        }
    }

    private void deletePreviousPosition(Long id){
        var alarmPosition = repository.findAlarmPositionByUserId(id);
        alarmPosition.ifPresent(value -> repository.deleteById(alarmPosition.get().getId()));
    }
}
