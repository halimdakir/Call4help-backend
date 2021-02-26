package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dtos.PositionDTO;
import com.solidbeans.call4help.entities.Location;
import com.solidbeans.call4help.entities.Position;
import com.solidbeans.call4help.entities.Users;
import com.solidbeans.call4help.exception.NotFoundException;
import com.solidbeans.call4help.exception.RegistrationException;
import com.solidbeans.call4help.repository.PositionRepository;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneOffset;

@Service
public class PositionServiceImplement implements PositionService{

    @Autowired
    private UserService userService;
    @Autowired
    private PositionRepository positionRepository;
    private final static GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 26910);

    @Override
    public Position savePosition(PositionDTO positionDTO) {

        if (positionDTO.getCoordinates() == null || positionDTO.getUserId() == null) {

            throw new RegistrationException("All fields are required!");

        } else {

            // Find user by userId
            var foundUser = userService.findUserByUserId(positionDTO.getUserId());

            if (foundUser != null) {

                //Check if This user has already position
                var position = positionRepository.findPositionByUsers_UserId(foundUser.getUserId());

                if (position.isPresent()){  //If position exist

                    position.get().setTime(Instant.now().atZone(ZoneOffset.UTC));
                    position.get().setCoordinates(geometryFactory.createPoint(new Coordinate(positionDTO.getCoordinates().getLng(), positionDTO.getCoordinates().getLat())));

                    return positionRepository.save(position.get());

                }else {  //if position does not exist

                    var newPosition = new Position(Instant.now().atZone(ZoneOffset.UTC), geometryFactory.createPoint(new Coordinate(positionDTO.getCoordinates().getLng(), positionDTO.getCoordinates().getLat())), foundUser);

                    return positionRepository.save(newPosition);
                }

            } else {

                throw new NotFoundException("User with id :" + positionDTO.getUserId() + " is not found");

            }

        }
    }

}
