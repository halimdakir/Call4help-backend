package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dto.PositionDTO;
import com.solidbeans.call4help.entity.Position;
import com.solidbeans.call4help.entity.Users;
import com.solidbeans.call4help.exception.NotFoundException;
import com.solidbeans.call4help.repository.PositionRepository;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PositionServiceImplement implements PositionService{

    @Autowired
    private PositionRepository repository;
    @Autowired
    private UserService userService;

    private final static GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 26910);


    @Override
    public Position createUserPosition(PositionDTO positionDTO, Long userId) {
        Users user = userService.findUserById(userId);
        Position position = new Position();
        if (user!=null){

            position.setDateTime(positionDTO.getDateTime());
            position.setCoordinates(geometryFactory.createPoint(new Coordinate(positionDTO.getCoordinates().getLng(), positionDTO.getCoordinates().getLat())));

            return repository.save(new Position(position.getDateTime(), position.getCoordinates(), user));

        }else {

            throw new NotFoundException("User with id :"+userId+" is not found");
        }
    }
}
