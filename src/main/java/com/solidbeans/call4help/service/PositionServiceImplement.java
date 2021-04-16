package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dtos.*;
import com.solidbeans.call4help.entities.Position;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class PositionServiceImplement implements PositionService{

    @Autowired
    private UserService userService;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private AlertService alertService;
    @Autowired
    private ProfileService profileService;

    private final static GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 26910);

    @Override
    public Position savePosition(String userId, PositionDTO positionDTO) {

        if (positionDTO.getCoordinates() == null || userId == null) {

            throw new RegistrationException("All fields are required!");

        } else {

            // Find user by userId
            var foundUser = userService.findUserByUserId(userId);

            if (foundUser != null) {

                //Check if This user has already position
                var position = positionRepository.findPositionByProfile_Users_UserId(foundUser.getUserId());

                if (position.isPresent()){  //If position exist

                    position.get().setTime(Instant.now().atZone(ZoneOffset.UTC));
                    position.get().setCoordinates(geometryFactory.createPoint(new Coordinate(positionDTO.getCoordinates().getLng(), positionDTO.getCoordinates().getLat())));

                    return positionRepository.save(position.get());

                }else {  //if position does not exist

                    var profile = profileService.findProfileByUserId(foundUser.getUserId());

                    if (profile != null){
                        var newPosition = new Position(Instant.now().atZone(ZoneOffset.UTC), geometryFactory.createPoint(new Coordinate(positionDTO.getCoordinates().getLng(), positionDTO.getCoordinates().getLat())), profile);

                        return positionRepository.save(newPosition);
                    }else {

                        throw new NotFoundException("Profile with user id :" + foundUser.getUserId() + " is not found");
                    }
                }

            } else {

                throw new NotFoundException("User with id :" + userId + " is not found");

            }

        }
    }

    //DISTANCE TO METRES AND DISTANCE LESS OR EQUAL THAN 500 METRES <RADIUS>
    @Override
    public List<NotificationMessageDTO> getNearestUsers(Long id) {

        List<DistanceDTO> unfilteredList = positionRepository.findNearestPersonList(id);

        var alert = alertService.findAlertById(id);

        if (alert.isPresent()){


                List<NotificationMessageDTO> nearestUsers = new ArrayList<>();

                for (DistanceDTO distanceDTO : unfilteredList){

                    //if (distanceDTO.getDistance() <= 50000000){ //TODO THE DISTANCE

                        var user = userService.findUserByPositionId(distanceDTO.getId());

                        user.ifPresent(users -> nearestUsers.add(new NotificationMessageDTO(alert.get().getId(), users.getId(), Math.round(distanceDTO.getDistance())+" meter bort!", alert.get().getUsers().getId())));


                    //}
                }

                return nearestUsers;

        }else {

            throw new NotFoundException("Alert with id:"+id+" not found!");

        }

    }

    @Override
    public List<Position> getAllPositions() {
        return (List<Position>) positionRepository.findAll();
    }

}
