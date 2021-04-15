package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dtos.DistanceDTO;
import com.solidbeans.call4help.dtos.NotificationMessageDTO;
import com.solidbeans.call4help.dtos.PositionDTO;
import com.solidbeans.call4help.entities.Position;
import com.solidbeans.call4help.exception.NotFoundException;
import com.solidbeans.call4help.exception.RegistrationException;
import com.solidbeans.call4help.repository.PositionRepository;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
public class PositionServiceImplement implements PositionService{

    private final UserService userService;
    private final PositionRepository positionRepository;
    private final AlertService alertService;
    private final ProfileService profileService;

    private final static GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 26910);

    public PositionServiceImplement(UserService userService, PositionRepository positionRepository, AlertService alertService, ProfileService profileService) {
        this.userService = userService;
        this.positionRepository = positionRepository;
        this.alertService = alertService;
        this.profileService = profileService;
    }

    @Override
    public Position savePosition(String userId, PositionDTO positionDTO) {

        if (positionDTO == null || userId == null) {

            throw new RegistrationException("All fields are required!");

        } else {

            // Find user by userId
            var foundUser = userService.findUserByUserId(userId);

            if (foundUser != null) {

                //Check if This user has already position
                var position = positionRepository.findPositionByProfile_Users_UserId(foundUser.getUserId());

                if (position.isPresent()){  //If position exist

                    position.get().setTime(Instant.now().atZone(ZoneOffset.UTC));
                    position.get().setCoordinates(geometryFactory.createPoint(new Coordinate(positionDTO.getLng(), positionDTO.getLat())));

                    return positionRepository.save(position.get());

                }else {  //if position does not exist

                    var profile = profileService.findProfileByUserId(foundUser.getUserId());

                    if (profile.isPresent()){
                        var newPosition = new Position(Instant.now().atZone(ZoneOffset.UTC), geometryFactory.createPoint(new Coordinate(positionDTO.getLng(), positionDTO.getLat())), profile.get());

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

                        user.ifPresent(users -> nearestUsers.add(new NotificationMessageDTO(users.getId(), Math.round(distanceDTO.getDistance())+" meter bort!", alert.get().getUsers().getId())));


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
