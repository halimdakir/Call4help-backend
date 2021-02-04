package com.solidbeans.call4help.service;

import com.solidbeans.call4help.dto.DistanceDTO;
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Service
public class PositionServiceImplement implements PositionService{

    @Autowired
    private PositionRepository repository;
    @Autowired
    private UserService userService;
    @PersistenceContext
    private EntityManager entityManager;

    private final static GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 26910);


    @Override
    public Position createUserPosition(PositionDTO positionDTO, Long userId) {
        Users user = userService.findUserById(userId);
        Position position = new Position();
        if (user!=null){

            position.setDateTime(positionDTO.getDateTime());
            position.setCoordinates(geometryFactory.createPoint(new Coordinate(positionDTO.getCoordinates().getLng(), positionDTO.getCoordinates().getLat())));

            //Keep only the last position for every user

            deletePreviousPosition(userId);

            repository.save(new Position(position.getDateTime(), position.getCoordinates(), user));
            entityManager.detach(position);

            return position;

        }else {

            throw new NotFoundException("User with id :"+userId+" is not found");
        }
    }

    @Override
    public List<DistanceDTO> nearestPersonsList(Long id) {
        return repository.findNearestPersonList(id);
    }

    private void deletePreviousPosition(Long id){
        var position = repository.findPositionByUserId(id);
        position.ifPresent(value -> repository.deleteById(position.get().getId()));
    }
}
